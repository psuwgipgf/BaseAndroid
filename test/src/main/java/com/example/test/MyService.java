package com.example.test;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;

public class MyService extends Service {

    private final IMyAidlInterface.Stub mAIDL=new IMyAidlInterface.Stub() {
        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public int add(int a, int b) throws RemoteException {
            return a+b;
        }
    };
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mAIDL;
    }
}
