package com.psuwgipgf.myapplication.presenter;

import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;

import com.libvirus.okhttplib.OkHttpManager;
import com.libvirus.okhttplib.callback.FileCallback;
import com.psuwgipgf.myapplication.R;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Subscriber;
import rx.schedulers.Schedulers;

public class UpdateService extends Service {

	public static String URL = "url";
	private NotificationManager mg;
	private Builder build;
	private String apk;
	private String name;
	private String path;

	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		mg = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		name = getResources().getString(R.string.app_name) + ".apk";
		if (Environment.getExternalStorageState().equals(
				Environment.MEDIA_MOUNTED)) {
			path = Environment.getExternalStoragePublicDirectory(
					Environment.DIRECTORY_DOWNLOADS).getAbsolutePath();
		} else {
			path = getFilesDir().getAbsolutePath();
		}
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		initParams(intent);
		Observable.create(new OnSubscribe<Boolean>() {

			@Override
			public void call(Subscriber<? super Boolean> arg0) {
				downLoad();
			}
		}).subscribeOn(Schedulers.io()).subscribe();
		return super.onStartCommand(intent, flags, startId);
	}

	private void initParams(Intent intent) {
		apk = intent.getStringExtra(URL);
		if (TextUtils.isEmpty(apk)) {
			stopSelf();
		}
	}

	private void downLoad() {
		showProgress(0);
		OkHttpManager.getRequest().setOkHttpClient(new OkHttpClient()).host("")
				.url(apk).exec(new FileCallback(path, name) {

					@Override
					public void result(File file) {
						mg.cancel(0);
						installApk(file);
					}

					@Override
					public void progress(int p) {
						showProgress(p);
					}

					@Override
					public void onFailure(Call call, IOException e) {
						mg.cancel(0);
					}

				});
	}

	private void showProgress(int p) {
		if (build == null) {
			build = new Notification.Builder(this);
			build.setSmallIcon(R.drawable.icon);
			build.setContentTitle("下载更新");
			build.setContentText("下载进度");
			build.setAutoCancel(true);
			build.setOngoing(true);
		}
		build.setProgress(100, p, false);
		mg.notify(0, build.build());
	}

	private void installApk(File file) {
		Intent in = new Intent(Intent.ACTION_VIEW);
		in.setDataAndType(Uri.fromFile(file),
				"application/vnd.android.package-archive");
		in.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(in);
	}
}
