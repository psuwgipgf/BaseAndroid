package com.psuwgipgf.myapplication.presenter;

import android.view.View;
import android.widget.TextView;

import com.psuwgipgf.myapplication.model.ModelManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * Created by psu on 2016/4/24.
 */
public class MainActivityPresenter {

    public void isFile(View v,String filename) {
        File file = new File(v.getContext().getCacheDir(), filename);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        String tem = null;
        try {
            fos = new FileOutputStream(file.getAbsolutePath());
            fos.write("asdfasdfasdfasdfasdf".getBytes());
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

    public void setTextView(final TextView view) {
        isFile(view,"123.txt");
        isFile(view,"abc.txt");
        List<Object>files=new ArrayList<>();
        files.add(new File(view.getContext().getCacheDir(), "123.txt"));
        files.add(new File(view.getContext().getCacheDir(), "abc.txt"));
        Map<String, Object> params = new HashMap();
        params.put("arr", "1");
//        ModelManager.apiGet("/", params)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String o) {
//                        view.setText(o);
//                    }
//                });
//        ModelManager.apiPost("/", params)
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        view.setText("没有网了……");
//                    }
//
//                    @Override
//                    public void onNext(String s) {
//                        view.setText(s);
//                    }
//                });
        ModelManager.apiPostFile("/", params,"text",files)
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
