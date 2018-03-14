package cn.nicemorning.flower;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

/**
 * Created by Nicemorning on 13-Mar-18.
 * In package cn.nicemorning.flower
 */

public class FlyActivity extends AppCompatActivity {
    private int screenWidth;
    private ImageView bird;
    private AnimationDrawable birdAnimation;
    private AnimatorSet birdAnimatorSet;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fly);
        getWindowWidth();
        bird = findViewById(R.id.bird);
        bird.setTranslationX(-screenWidth);
        birdAnimation = (AnimationDrawable) bird.getDrawable();
        birdAnimatorSet = new AnimatorSet();
        ObjectAnimator birdAnimatorR = ObjectAnimator.ofFloat(bird, "translationX",
                screenWidth);
        birdAnimatorR.setDuration(30 * 1000);
        birdAnimatorR.setInterpolator(new LinearInterpolator());
        birdAnimatorR.setRepeatCount(-1);
        birdAnimatorSet.play(birdAnimatorR);
        birdAnimation.start();
        birdAnimatorSet.start();
    }

    private void getWindowWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
    }

}
