package com.example.animationsample;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class CutInAnimationActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cutin_animation);


    }

    public void startAnimation(View view) {
        switch (view.getId()) {

            case R.id.cut_in_action:
                RelativeLayout cutIn = (RelativeLayout) findViewById(R.id.cutInPartial);
                cutIn.setVisibility(View.VISIBLE);

                Point windowSize = new Point();
                getWindowManager().getDefaultDisplay().getSize(windowSize);
                ObjectAnimator slideIn = ObjectAnimator.ofFloat(cutIn, "translationX", windowSize.x, 0).setDuration(1000);
                ObjectAnimator stop = ObjectAnimator.ofFloat(cutIn, "translationX", 0, 0).setDuration(2000);
                ObjectAnimator slideOut = ObjectAnimator.ofFloat(cutIn, "translationX", 0, -windowSize.x).setDuration(1000);
                AnimatorSet animators = new AnimatorSet();
                animators.playSequentially(slideIn, stop, slideOut);
                animators.start();

                break;
        }

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
