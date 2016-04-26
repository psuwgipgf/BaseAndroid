package com.psuwgipgf.myapplication.model;

import com.psuwgipgf.myapplication.eventbus.RxBus;
import com.psuwgipgf.myapplication.eventbus.RxBusType;
import com.psuwgipgf.myapplication.model.api.ApiHelper;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by psu on 2016/4/5.
 */
public class ModelManager {


    public ModelManager() {
    }

    public static Observable<String> apiGet(final String url, final Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<Response>() {

            @Override
            public void call(Subscriber<? super Response> o) {
                o.onNext(ApiHelper.get(url, params));
            }
        }).map(new Func1<Response, String>() {
            @Override
            public String call(Response o) {
                if (o != null) {
                    try {
                        return o.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                RxBus.send(new RxBusType(RxBusType.NETWORK_STATUS));
                return null;
            }
        }).subscribeOn(Schedulers.io());
    }

    public static Observable<String> apiPost(final String url, final Map<String, String> params) {
        return Observable.create(new Observable.OnSubscribe<Response>() {

            @Override
            public void call(Subscriber<? super Response> o) {
                o.onNext(ApiHelper.post(url, params));
            }
        }).map(new Func1<Response, String>() {
            @Override
            public String call(Response o) {
                if (o != null) {
                    try {
                        return o.body().string();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                RxBus.send(new RxBusType(RxBusType.NETWORK_STATUS));
                return null;
            }
        }).subscribeOn(Schedulers.io());

    }


}
