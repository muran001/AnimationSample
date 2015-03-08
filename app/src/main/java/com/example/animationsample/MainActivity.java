package com.example.animationsample;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Shader;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.CycleInterpolator;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_world);
        Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(circleBitmap);
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setShader(shader);

        c.drawCircle(bitmap.getWidth()/2, bitmap.getWidth()/2, bitmap.getWidth()/3, paint);

        ImageView imageView = (ImageView) findViewById(R.id.topImage);
        imageView.setImageBitmap(circleBitmap);

        startAnimation(imageView);
    }

    public void startAnimation(ImageView view) {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        ObjectAnimator animation1 = ObjectAnimator.ofFloat(view, "x", size.x/2-50, size.x/2-250);
        animation1.setDuration(50000);
        ObjectAnimator animation2 = ObjectAnimator.ofFloat(view, "x", size.x/2-50, size.x/2+500);
        animation2.setDuration(50000);

        List<Animator> animators = new ArrayList<Animator>();
        animators.add(animation1);
        animators.add(animation2);
        AnimatorSet set = new AnimatorSet();
        set.playSequentially(animators);
        set.setInterpolator(new CycleInterpolator(20));

        set.start();

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void startBasicAnimationActivity(View view) {
        Intent intent = new Intent(this, BasicAnimationActivity.class);
        startActivity(intent);
    }

    public void startHitAnimationActivity(View view) {
        Intent intent = new Intent(this, HitAnimationActivity.class);
        startActivity(intent);
    }
}
