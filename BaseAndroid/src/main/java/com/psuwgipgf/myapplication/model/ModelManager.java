package com.psuwgipgf.myapplication.model;

import android.text.TextUtils;
import android.util.Log;

import com.psuwgipgf.myapplication.model.api.ApiHelper;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;
import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by psu on 2016/4/5.
 */
public class ModelManager {

    public static Observable apiGet(final String url, final Map<String, String> params) {
        Observable observable = Observable.create(new Observable.OnSubscribe<Response>() {

            @Override
            public void call(Subscriber<? super Response> o) {
                o.onNext(ApiHelper.get(url, params));
            }
        }).map(new Func1<Response,String>() {
            @Override
            public String call(Response o) {
                Log.e("ModelManager",Thread.currentThread().getName());
                try {
                    return o.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }).filter(new Func1<String, Boolean>() {

            @Override
            public Boolean call(String response) {
                Log.e("ModelManager",Thread.currentThread().getName());
                if(TextUtils.isEmpty(response)){
                    return false;
                }
                return true;
            }
        }).subscribeOn(Schedulers.io());

        return observable;
    }


}
