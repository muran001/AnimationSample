package com.example.animationsample;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.Random;

public class HitAnimationActivity extends ActionBarActivity {
    private ObjectAnimator animation1;
    private ObjectAnimator animation2;
    private ObjectAnimator animation3;
    private Button button;
    private Random randon;
    private int width;
    private int height;
    private int degree;
    private AnimatorSet set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.target);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Point size = new Point();
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(size);
        width = size.x;
        height = size.y;
        degree = 0;
        randon = new Random();
        button = (Button) findViewById(R.id.button1);

        set = createAnimation();
        set.start();
        set.addListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {
                int nextX = randon.nextInt(width);
                int nextY = randon.nextInt(height);
                int nextDegree = randon.nextInt(360);
                animation1 = ObjectAnimator.ofFloat(button, "x", button.getX(),
                        nextX);
                animation1.setDuration(2000);
                animation2 = ObjectAnimator.ofFloat(button, "y", button.getY(),
                        nextY);
                animation2.setDuration(2000);
                animation3 = ObjectAnimator.ofFloat(button, "rotation", degree, nextDegree);
                animation3.setDuration(2000);
                degree = nextDegree;
                set.playTogether(animation1, animation2, animation3);
                set.start();
            }
        });
    }

    public void onClick(View view) {
        String string = button.getText().toString();
        int hitTarget = Integer.valueOf(string) + 1;
        button.setText(String.valueOf(hitTarget));
    }

    private AnimatorSet createAnimation() {
        int nextX = randon.nextInt(width);
        int nextY = randon.nextInt(height);
        int nextDegree = randon.nextInt(360);
        animation1 = ObjectAnimator.ofFloat(button, "x", nextX);
        animation1.setDuration(2000);
        animation2 = ObjectAnimator.ofFloat(button, "y", nextY);
        animation2.setDuration(2000);
        animation3 = ObjectAnimator.ofFloat(button, "rotation", degree, nextDegree);
        animation3.setDuration(2000);
        degree = nextDegree;


        AnimatorSet set = new AnimatorSet();

        set.playTogether(animation1, animation2, animation3);
        return set;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}