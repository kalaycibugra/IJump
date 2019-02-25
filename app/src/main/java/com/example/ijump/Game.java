package com.example.ijump;

import android.animation.ValueAnimator;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;


public class Game extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        final ConstraintLayout board1=(ConstraintLayout)findViewById(R.id.board);
        final TextView animateTextView = (TextView)findViewById(R.id.tv_animate);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 500f);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
        valueAnimator.setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float progress = (float) animation.getAnimatedValue();
                animateTextView.setTranslationY(progress);
                // no need to use invalidate() as it is already present in             //the text view.
            }
        });
        valueAnimator.start();

        final Button button =(Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(500f, 0f);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
                valueAnimator.setDuration(1500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float progress = (float) animation.getAnimatedValue();
                        animateTextView.setTranslationY(progress);
                        // no need to use invalidate() as it is already present in             //the text view.
                    }
                });
                valueAnimator.start();
            }
        });
        board1.setOnTouchListener(new OnSwipeTouchListener(Game.this){

            public void onSwipeTop() {

                ValueAnimator valueAnimator = ValueAnimator.ofFloat(500f, 0f);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
                valueAnimator.setDuration(1500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float progress = (float) animation.getAnimatedValue();
                        animateTextView.setTranslationY(progress);
                        // no need to use invalidate() as it is already present in             //the text view.
                    }
                });
                valueAnimator.start();
            }

            public void onSwipeRight() {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 500f);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
                valueAnimator.setDuration(1500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float progress = (float) animation.getAnimatedValue();
                        animateTextView.setTranslationX(progress);
                        // no need to use invalidate() as it is already present in             //the text view.
                    }
                });
                valueAnimator.start();
            }

            public void onSwipeLeft() {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(500f, 0f);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
                valueAnimator.setDuration(1500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float progress = (float) animation.getAnimatedValue();
                        animateTextView.setTranslationX(progress);
                        // no need to use invalidate() as it is already present in             //the text view.
                    }
                });
                valueAnimator.start();
            }

            public void onSwipeBottom() {
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 500f);
                valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator()); // increase the speed first and then decrease
                valueAnimator.setDuration(1500);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float progress = (float) animation.getAnimatedValue();
                        animateTextView.setTranslationY(progress);
                        // no need to use invalidate() as it is already present in             //the text view.
                    }
                });
                valueAnimator.start();
            }


        });
    }
}
