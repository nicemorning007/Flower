package cn.nicemorning.flower;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

import cn.nicemorning.flower.database.dao.DandelionModel;

/**
 * Created by Nicemorning on 14-Mar-18.
 * In package cn.nicemorning.flower
 */

public class DandelionView extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mHolder;
    private Thread mThread;
    private Canvas mCanvas;
    private boolean isRunning;
    private Bitmap[] bitmaps = new Bitmap[5];
    private Random random = new Random();
    private int drawCounts = 3;
    private ArrayList<DandelionModel> dandelionModels_S = new ArrayList<DandelionModel>();
    private ArrayList<DandelionModel> dandelionModels_M = new ArrayList<DandelionModel>();
    private ArrayList<DandelionModel> dandelionModels_L = new ArrayList<DandelionModel>();
    private ArrayList<DandelionModel> dandelionModels_X = new ArrayList<DandelionModel>();
    private ArrayList<DandelionModel> dandelionModels_XX = new ArrayList<DandelionModel>();
    private int screenWidth, screenHeight;

    public DandelionView(Context context) {
        super(context);
        init();
    }

    public DandelionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DandelionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        screenWidth = getResources().getDisplayMetrics().widthPixels;
        screenHeight = getResources().getDisplayMetrics().heightPixels;
        mHolder = this.getHolder();
        mHolder.addCallback(this);
        setZOrderOnTop(true);
        mHolder.setFormat(PixelFormat.TRANSPARENT);
        bitmaps[0] = BitmapFactory.decodeResource(getResources(), R.mipmap.mrkj_dandelion_30);
        bitmaps[1] = BitmapFactory.decodeResource(getResources(), R.mipmap.mrkj_dandelion_40);
        bitmaps[2] = BitmapFactory.decodeResource(getResources(), R.mipmap.mrkj_dandelion_50);
        bitmaps[3] = BitmapFactory.decodeResource(getResources(), R.mipmap.mrkj_dandelion_60);
        bitmaps[4] = BitmapFactory.decodeResource(getResources(), R.mipmap.mrkj_dandelion_70);
        for (int i = 0; i < drawCounts; i++) {
            dandelionModels_S.add(new DandelionModel(
                    random.nextInt(screenWidth) + (bitmaps[0].getWidth() >> 1),
                    random.nextInt(screenHeight) + (bitmaps[0].getHeight() >> 1), 2, 4));
            dandelionModels_M.add(new DandelionModel(
                    random.nextInt(screenWidth) + (bitmaps[1].getWidth() >> 1),
                    random.nextInt(screenHeight) + (bitmaps[1].getHeight() >> 1), 4, 4));
            dandelionModels_L.add(new DandelionModel(
                    random.nextInt(screenWidth) + (bitmaps[2].getWidth() >> 1),
                    random.nextInt(screenHeight) + (bitmaps[2].getHeight() >> 1), 6, 4));
            dandelionModels_X.add(new DandelionModel(
                    random.nextInt(screenWidth) + (bitmaps[3].getWidth() >> 1),
                    random.nextInt(screenHeight) + (bitmaps[3].getHeight() >> 1), 8, 4));
            dandelionModels_XX.add(new DandelionModel(
                    random.nextInt(screenWidth) + (bitmaps[4].getWidth() >> 1),
                    random.nextInt(screenHeight) + (bitmaps[4].getHeight() >> 1), 10, 4));
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mThread = new Thread(this);
        mThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    @Override
    public void run() {
        while (isRunning) {
            try {
                mCanvas = mHolder.lockCanvas();
                if (mCanvas != null) {
                    mCanvas.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR);
                    for (int i = 0; i < drawCounts; i++) {
                        mCanvas.drawBitmap(bitmaps[0], dandelionModels_S.get(i).getPointX(),
                                dandelionModels_S.get(i).getPointY(), null);
                        mCanvas.drawBitmap(bitmaps[1], dandelionModels_M.get(i).getPointX(),
                                dandelionModels_M.get(i).getPointY(), null);
                        mCanvas.drawBitmap(bitmaps[2], dandelionModels_L.get(i).getPointX(),
                                dandelionModels_L.get(i).getPointY(), null);
                        mCanvas.drawBitmap(bitmaps[3], dandelionModels_X.get(i).getPointX(),
                                dandelionModels_X.get(i).getPointY(), null);
                        mCanvas.drawBitmap(bitmaps[4], dandelionModels_XX.get(i).getPointX(),
                                dandelionModels_XX.get(i).getPointY(), null);
                    }
                    for (int i = 0; i < drawCounts; i++) {
                        offsetXY(dandelionModels_S.get(i), bitmaps[0]);
                        offsetXY(dandelionModels_M.get(i), bitmaps[1]);
                        offsetXY(dandelionModels_L.get(i), bitmaps[2]);
                        offsetXY(dandelionModels_X.get(i), bitmaps[3]);
                        offsetXY(dandelionModels_XX.get(i), bitmaps[4]);
                    }
                    Thread.sleep(150);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void offsetXY(DandelionModel dandelionModel, Bitmap bitmap) {
        if (dandelionModel.getPointY() > screenHeight) {
            dandelionModel.setPointY(bitmap.getHeight() >> 1);
        }
        dandelionModel.setPointY(dandelionModel.getPointY()
                + dandelionModel.getPortOffset());
        if (dandelionModel.getPointX() > screenWidth || dandelionModel.getPointX() < 0) {
            dandelionModel.setPointX(bitmap.getWidth() >> 1);
        }
        dandelionModel.setPointX(dandelionModel.getPointX() +
                ((random.nextInt(2) << 1) - 1) * dandelionModel.getLandOffset());
    }

    @Override
    protected void onVisibilityChanged(@NonNull View changedView, int visibility) {
        super.onVisibilityChanged(changedView, visibility);
        isRunning = (visibility == VISIBLE);
    }
}
