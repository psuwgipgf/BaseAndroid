package com.psu.myapp;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class ActivityAnimaction extends Activity {

    private View mContentView;
    private View mLoadingView;
    private int mShortAnimationDuration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_animation);
        mContentView = findViewById(R.id.content);
        mLoadingView = findViewById(R.id.load_progress);
        mShortAnimationDuration = getResources().getInteger(
                android.R.integer.config_shortAnimTime);
        initAnim();
    }

    private void initAnim(){
        mContentView.setAlpha(0f);
        mContentView.setVisibility(View.VISIBLE);
        mContentView.animate()
                .alpha(1f)
                .setDuration(mShortAnimationDuration);
        mLoadingView.animate()
                .alpha(0f)
                .setDuration(mShortAnimationDuration)
                .setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLoadingView.setVisibility(View.GONE);
                    }
                });

    }
}
