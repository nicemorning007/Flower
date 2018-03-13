package cn.nicemorning.flower.database;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import cn.nicemorning.flower.R;

/**
 * Created by Nicemorning on 13-Mar-18.
 * In package cn.nicemorning.flower.database
 */

public class MyDataHelper {
    private DatasDao datasDao;                //数据库操作类
    private int[] bimmapID;                //花的图片资源ID
    private Bitmap[] flowers;                //花的图片
    private String[] flowerName;            //花的名称
    private ArrayList<Quotes> quotesList;    //名言警句集合

    private static class SingletonHolder {
        private static final MyDataHelper INSTANCE = new MyDataHelper();//创建对象
    }

    private MyDataHelper() {
    }                //私有的构造方法，防止外部来调用该方法创建新的对象

    public static final MyDataHelper getInstance() {    //获取对象
        return SingletonHolder.INSTANCE;                //返回对象
    }

    /**
     * 返回图片资源ID数组
     *
     * @return
     */
    public int[] getBitmapID() {
        if (bimmapID == null) {               //当该数组为空时创建对象，否则直接返回
            bimmapID = new int[]{R.mipmap.mrkj_flower_01, R.mipmap.mrkj_flower_02
                    , R.mipmap.mrkj_flower_03, R.mipmap.mrkj_flower_04, R.mipmap.mrkj_flower_05
                    , R.mipmap.mrkj_flower_06, R.mipmap.mrkj_flower_07, R.mipmap.mrkj_flower_08
                    , R.mipmap.mrkj_flower_09, R.mipmap.mrkj_flower_10};
        }
        return bimmapID;                    //返回图片资源的数组
    }

    /**
     * 花朵bitmap数组
     *
     * @param context
     * @return
     */
    public Bitmap[] getBitmapArray(Context context) {
        int[] resID = getBitmapID();    //获取图片的资源数组
        flowers = new Bitmap[10];        //实例化数组并指定数组的长度为10
        for (int i = 0; i < flowers.length; i++) {//将资源文件转换成Bitmmap存到数组中
            flowers[i] = BitmapFactory.decodeResource(context.getResources(), resID[i]);
        }
        return flowers;                    //返回图片的数组
    }

    /**
     * 花的名称
     */
    public String[] getFlowerNames() {
        if (flowerName == null) {            //当对象为空时创建String类型的数组来存放花的名字
            flowerName = new String[]{"勿忘我", "三色堇", "金盏菊"
                    , "雏菊", "桔梗花", "鸡蛋花", "石竹", "莺萝", "荷兰菊", "百合"};
        }
        return flowerName;                    //返回花的名称的数组
    }
}
