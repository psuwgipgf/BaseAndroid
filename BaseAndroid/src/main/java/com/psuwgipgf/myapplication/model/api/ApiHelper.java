package com.psuwgipgf.myapplication.model.api;


import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.libvirus.okhttplib.OkHttpManager;
import com.libvirus.okhttplib.utils.CookieInterface;

import java.util.List;
import java.util.Map;

import okhttp3.Response;

/**
 * Created by psu on 2016/4/5.
 */
public class ApiHelper {

    private static Application app;
    private static String mCookie;
    private final static String key = "Set-Cookie";//JSESSIONID

    // 初始化
    public static void init(Application app) {
        ApiHelper.app = app;
        OkHttpManager.getInstace().host(UrlInterface.HOST).log("psuwgipgf")
                .setCookie(new CookieInterface() {

                    @Override
                    public void saveCookie(String cookie) {
                        setCookie(cookie);
                    }

                    @Override
                    public String getKey() {
                        return key;
                    }

                    @Override
                    public String getCookie() {
                        return getSharedPreCookie();
                    }
                });
    }

    private static String getSharedPreCookie() {
        if (TextUtils.isEmpty(mCookie)) {
            SharedPreferences sp = app.getSharedPreferences("_cookie",
                    Context.MODE_PRIVATE);
            mCookie=sp.getString(key,"");
        }
        return mCookie;
    }

    private static void setCookie(String str) {
        SharedPreferences sp = app.getSharedPreferences("_cookie",
                Context.MODE_PRIVATE);
        sp.edit().putString(key,str).commit();
        mCookie=str;
    }

    public static Response get(String url, Map<String, String> params) {
        Response response = OkHttpManager.getRequest().url(url).setParams(params).exec();
        return response;
    }

    public static Response post(String url, Map<String, String> params) {
        Response response = OkHttpManager.postRequest().url(url).setParam(params).exec();
        return response;
    }

    public static Response postFile(String url, Map<String, Object> p, String arrkey, List<Object> arr) {

        Response response = OkHttpManager.postFileRequest().url(url).setParam(p).addParams(arrkey, arr).exec();
        return response;
    }


}
