package com.psuwgipgf.myapplication.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.psuwgipgf.myapplication.R;
import com.psuwgipgf.myapplication.eventbus.RxBus;
import com.psuwgipgf.myapplication.eventbus.RxBusType;

import rx.functions.Action1;

public class MainActivity extends BaseActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.e("TAGasdf","asdfasdfasdfasdfasdfasdf");
        RxBus.toObservable()
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {
                        Log.e("Psuwgipgf",Thread.currentThread().getName());
                    }
                });
        RxBus.toObservable(1,2,3)
                .subscribe(new Action1<Object>() {
                    @Override
                    public void call(Object o) {

                        Log.e("Psuwgipgf",Thread.currentThread().getName());
                    }
                });
    }

    public void onClick(View v){
        RxBus.send(new RxBusType(RxBusType.NETWORK_STATUS));
    }

}
