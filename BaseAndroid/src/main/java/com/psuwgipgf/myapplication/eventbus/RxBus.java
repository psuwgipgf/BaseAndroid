package com.psuwgipgf.myapplication.eventbus;

import android.util.Log;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.subjects.PublishSubject;
import rx.subjects.SerializedSubject;
import rx.subjects.Subject;

/**
 * Created by psu on 2016/4/20.
 */
public class RxBus {

    private static final Subject<RxBusType,RxBusType> mSubject = new SerializedSubject(PublishSubject.create());

    public static void send(RxBusType o) {
        mSubject.onNext(o);
    }

    public static Observable<RxBusType> toObservable(final int... type) {
        return mSubject.filter(new Func1<RxBusType, Boolean>() {

            @Override
            public Boolean call(RxBusType o) {
                for (int i : type) {
                    if (o.type == i) {
                        return true;
                    }
                }
                return false;
            }
        });
    }

    public static Observable<RxBusType> toObservable() {
        return mSubject;
    }

}
