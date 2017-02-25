package com.libvirus.application;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.example.test.IMyAidlInterface;
import com.libvirus.application.view.CircleImageView;
import com.libvirus.application.view.RoundImaveView;


public class MainActivity extends AppCompatActivity {

    private RoundImaveView my_image;
    private CircleImageView my_image2;//
    IMyAidlInterface ibinder;

    private ServiceConnection sc=new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            ibinder= IMyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        my_image = (RoundImaveView) findViewById(R.id.my_image);
        my_image2 = (CircleImageView) findViewById(R.id.my_image2);
        Intent in=new Intent("com.example.test.MyServer");
//        in.addCategory(Intent.CATEGORY_DEFAULT);
        in.setPackage("com.example.test");
        bindService(in,sc, Service.BIND_AUTO_CREATE);
        my_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int i=ibinder.add(10,100);
                    Toast.makeText(MainActivity.this,""+i, Toast.LENGTH_SHORT).show();
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        my_image2.setFillColor(Color.parseColor("#0000ff"));
//        my_image.setImageDrawable(ContextCompat.getDrawable(this,R.drawable.a2));
    }

}
