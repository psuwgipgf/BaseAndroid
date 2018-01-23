package com.psuwgipgf.myapplication.app;

import android.app.Application;

import com.psuwgipgf.myapplication.model.ModelManager;

/**
 * Created by psu on 2016/4/6.
 */
public class AppContext extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ModelManager.initModelManager(this);
    }
}
