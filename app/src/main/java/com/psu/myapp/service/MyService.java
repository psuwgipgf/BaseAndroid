package com.psu.myapp.service;

import android.app.Notification;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.Toast;

import com.psu.myapp.R;

public class MyService extends Service  {

    private MyIBind mIBind = new MyIBind();

    public MyService() {

    }

    @Override
    public void onCreate() {
        super.onCreate();
        Toast.makeText(this, "MyService is Running ", Toast.LENGTH_LONG).show();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "onStartCommand…………"+startId, Toast.LENGTH_LONG).show();
        Notification notifi=new NotificationCompat.Builder(this)
                .setContentTitle("Test NotifiCation")
                .setContentText("这是正文")
                .setSmallIcon(R.mipmap.ic_launcher)
                .build();
        startForeground(1,notifi);
        return START_STICKY;

    }

    public void stopService(){
        stopSelf();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "onDestroy…………", Toast.LENGTH_LONG).show();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mIBind;
    }

    public class MyIBind extends Binder {

        public  void DownLoad(){
            Toast.makeText(MyService.this,"DownLoad______",Toast.LENGTH_LONG).show();
        }
    }
}
