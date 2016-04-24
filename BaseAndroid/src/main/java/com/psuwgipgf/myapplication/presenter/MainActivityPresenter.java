package com.psuwgipgf.myapplication.presenter;

import android.widget.TextView;

import com.psuwgipgf.myapplication.model.ModelManager;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by psu on 2016/4/24.
 */
public class MainActivityPresenter {

    public void setTextView(final TextView view) {
        Observable observable = ModelManager.apiGet("/", null);
        observable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String o) {
                        view.setText(o);
                    }
                });
    }


}
