package com.psuwgipgf.myapplication.app;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by psu on 2016/4/6.
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LeakCanary.install(this);//内存泄露检测
    }
}
