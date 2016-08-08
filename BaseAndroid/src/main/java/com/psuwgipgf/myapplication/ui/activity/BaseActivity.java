package com.psuwgipgf.myapplication.ui.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.psuwgipgf.myapplication.eventbus.RxBus;
import com.psuwgipgf.myapplication.eventbus.RxBusType;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by psu on 2016/4/6.
 */
public class BaseActivity extends FragmentActivity {

    private CompositeSubscription compositeSubscription = new CompositeSubscription();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initRxBus();

    }

    private void initRxBus() {
        compositeSubscription.add(RxBus.toObservable(RxBusType.QUIT)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxBusType>() {
                    @Override
                    public void call(RxBusType o) {
                        finish();
                    }
                }));
        compositeSubscription.add(RxBus.toObservable(RxBusType.NETWORK_STATUS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RxBusType>() {
                    @Override
                    public void call(RxBusType o) {
                        netWorkStatus();
                    }
                }));
    }

    protected void netWorkStatus() {

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        compositeSubscription.unsubscribe();
    }
}
