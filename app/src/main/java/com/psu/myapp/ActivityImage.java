package com.psu.myapp;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.psu.myapp.util.ImageLoad;

public class ActivityImage extends Activity implements View.OnClickListener {

    private ImageView image;
    private int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_camera);
        image = (ImageView) findViewById(R.id.image);
        ImageLoad imageLoad = new ImageLoad(this, image);
        imageLoad.execute(R.drawable.asdlfjei);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
                AlphaAnimation alpha = new AlphaAnimation(1f, 0f);
                alpha.setDuration(500);
                alpha.setRepeatMode(Animation.REVERSE);
                alpha.setStartOffset(1000);
                alpha.setStartTime(1000);
                image.startAnimation(alpha);
                break;
            case R.id.button2:
                doudongAnimation();
                break;
            case R.id.button3:
//                Animation scale = new ScaleAnimation(0.2f, 2.0f, 0.2f, 2.0f,
//                        Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
//                        0.5f);
//                scale.setDuration(500);
//                image.startAnimation(scale);
                AnimationDrawable ad=new AnimationDrawable();
                ad.addFrame(getResources().getDrawable(R.drawable.cloundlslllsdk),100);
                image.setImageDrawable(ad);
                ad.start();
                break;
            case R.id.button4:
                RotateAnimation rotate = new RotateAnimation(0, 360, image.getMeasuredWidth() / 2, image.getMeasuredHeight() / 2);
                rotate.setDuration(50);
                rotate.setRepeatCount(-1);
                image.startAnimation(rotate);
                break;
            default:
                break;
        }
    }

    private void doudongAnimation(){
        final TranslateAnimation translate1 = new TranslateAnimation(-20, 20, -20, 20);
        final TranslateAnimation translate2 = new TranslateAnimation(20, -20, 20, -20);
        translate1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(i>3){
                    i=0;
                    return;
                }
                image.startAnimation(translate2);
                i+=1;
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        translate2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                image.startAnimation(translate1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        translate1.setDuration(50);
        translate2.setDuration(50);
        image.startAnimation(translate1);
    }
}
