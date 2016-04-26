package com.psuwgipgf.myapplication.presenter;

import android.widget.TextView;

import com.psuwgipgf.myapplication.model.ModelManager;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by psu on 2016/4/24.
 */
public class MainActivityPresenter {

    public void setTextView(final TextView view) {
//        ModelManager.apiGet("/", null)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String o) {
//                        view.setText(o);
//                    }
//                });
        ModelManager.apiPost("/", null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        view.setText("没有网了……");
                    }

                    @Override
                    public void onNext(String s) {
                        view.setText(s);
                    }
                });
    }


}
