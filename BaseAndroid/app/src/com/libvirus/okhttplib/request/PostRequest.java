package com.libvirus.okhttplib.request;

import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by psu on 2016/4/16.
 */
public class PostRequest extends OkHttpRequest {

    private Request.Builder builder;
    private FormBody.Builder builderBody = new FormBody.Builder();

    public PostRequest() {
        builder = new Request.Builder();
    }

    public PostRequest setOkHttpClient(OkHttpClient p) {
        mOkHttpClient = p;
        return this;
    }

    public PostRequest host(String u) {
        host = u;
        return this;
    }

    public PostRequest url(String u) {
        url = u;
        return this;
    }

    public PostRequest tag(String u) {
        builder.tag(u);
        return this;
    }

    public PostRequest addHeader(String k, String v) {
        builder.addHeader(k, v);
        return this;
    }

    public PostRequest setHeader(Map<String, String> p) {
        if (p != null) {
            for (String key : p.keySet()) {
                builder.header(key, p.get(key));
            }
        }
        return this;
    }

    public PostRequest addParam(String k, String v) {
        builderBody.add(k, v);
        return this;
    }

    public PostRequest setParam(Map<String, String> p) {
        if (p != null) {
            for (String key : p.keySet()) {
                builderBody.add(key, p.get(key));
            }
        }

        return this;
    }

    public PostRequest addParams(String key, List<String> p) {
        if (p != null) {
            for (String item : p) {
                builderBody.add(key, item);
            }
        }
        return this;
    }

    @Override
    protected void build() {
        builder.url(host + url)
                .post(builderBody.build());
        request = builder.build();
    }
}
