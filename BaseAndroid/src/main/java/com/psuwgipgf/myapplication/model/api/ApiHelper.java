package com.psuwgipgf.myapplication.model.api;


import com.libvirus.okhttplib.OkHttpManager;

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
        Response response = OkHttpManager.getRequest().url(url).setParams(params).exec();
        return response;
    }

    public static Response post(String url, Map<String, String> params) {
        Response response = OkHttpManager.postRequest().url(url).setParam(params).exec();
        return response;
    }

    public static Response postFile(String url, Map<String, Object> p) {
        Response response = OkHttpManager.postFileRequest().url(url).setParam(p).exec();
        return response;
    }


}
