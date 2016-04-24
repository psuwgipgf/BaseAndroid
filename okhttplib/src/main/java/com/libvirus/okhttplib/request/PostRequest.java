package com.libvirus.okhttplib.request;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by psu on 2016/4/16.
 */
public class PostRequest extends OkHttpRequest {

    public PostRequest setOkHttpClient(OkHttpClient p) {
        mOkHttpClient = p;
        return this;
    }

    @Override
    public PostRequest host(String u) {
        host = u;
        return this;
    }

    @Override
    public PostRequest url(String u) {
        url = u;
        return this;
    }

    @Override
    public PostRequest tag(String u) {
        tag = u;
        return this;
    }

    @Override
    public PostRequest addHeader(String k, String v) {
        if (mHeader == null) {
            mHeader = new LinkedHashMap<>();
        }
        mHeader.put(k, v);
        return this;
    }

    @Override
    public PostRequest setHeader(Map<String, String> p) {
        mHeader = p;
        return this;
    }

    @Override
    public PostRequest addParams(String k, String v) {
        if (mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(k, v);
        return this;
    }

    @Override
    public PostRequest setParams(Map<String, String> p) {
        mParams = p;
        return this;
    }

    @Override
    protected void build() {
        FormBody.Builder builder = new FormBody.Builder();
        if (mParams != null) {
            for (String key : mParams.keySet()) {
                builder.add(key, mParams.get(key));
            }
        }
        Request.Builder requestBuild = new Request.Builder()
                .url(host + url)
                .post(builder.build());

        if (mHeader != null) {
            for (String key : mHeader.keySet()) {
                requestBuild.addHeader(key, mHeader.get(key));
            }
        }
        request = requestBuild.build();
    }
}
