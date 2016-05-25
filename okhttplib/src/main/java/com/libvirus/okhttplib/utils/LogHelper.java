package com.libvirus.okhttplib.utils;

import android.text.TextUtils;
import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by psu on 2016/4/9.
 */
public class LogHelper implements Interceptor {

    public static final String TAG = "OkHttpLibLoghelper";
    private String tag = "";

    public LogHelper() {
        tag = TAG;
    }

	public LogHelper(String t) {
        if (!TextUtils.isEmpty(t)) {
            tag = t;
        }
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        showRequest(request);
        Response response = chain.proceed(request);
        showResponse(response);
        return response;
    }

    private void showRequest(Request request) {

        Log.e(tag, request.toString());

    }

    private void showResponse(Response response) {
        Log.e(tag, response.toString());
    }
}
