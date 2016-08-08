package com.psuwgipgf.myapplication.app;

import android.app.Application;

import com.psuwgipgf.myapplication.model.ModelManager;
import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by psu on 2016/4/6.
 */
public class AppContext extends Application {
	@Override
	public void onCreate() {
		super.onCreate();
		ModelManager.initModelManager(this);
		CrashReport
		.initCrashReport(getApplicationContext(), "900029425", false);
	}
}
