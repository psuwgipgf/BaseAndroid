package com.psuwgipgf.myapplication.model.api;


import android.nfc.Tag;

import com.libvirus.okhttplib.OkHttpManager;

import java.io.IOException;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by psu on 2016/4/5.
 */
public class ApiHelper {

    static {
        init();
    }

    //初始化
    private static void init() {
        OkHttpManager.getInstace()
                .host(UrlInterface.HOST)
                .log("psuwgipgf");
    }

    public static Response get(String url, Map<String, String> params) {
        String result = "";
        Response response = OkHttpManager.getRequest().url(url).setParams(params).exec();
        return response;
    }


}
