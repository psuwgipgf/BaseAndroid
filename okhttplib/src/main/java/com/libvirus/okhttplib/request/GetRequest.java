package com.libvirus.okhttplib.request;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by psu on 2016/4/10.
 */
public class GetRequest extends OkHttpRequest {


    public GetRequest setOkHttpClient(OkHttpClient p) {
        mOkHttpClient = p;
        return this;
    }

    @Override
    public GetRequest host(String u) {
        host = u;
        return this;
    }

    @Override
    public GetRequest url(String u) {
        url = u;
        return this;
    }

    @Override
    public GetRequest tag(String u) {
        tag = u;
        return this;
    }

    @Override
    public GetRequest addHeader(String k, String v) {
        if (mHeader == null) {
            mHeader = new LinkedHashMap<>();
        }
        mHeader.put(k, v);
        return this;
    }

    @Override
    public GetRequest setHeader(Map<String, String> p) {
        mHeader = p;
        return this;
    }

    @Override
    public GetRequest addParams(String k, String v) {
        if (mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(k, v);
        return this;
    }

    @Override
    public OkHttpRequest setParams(Map<String, String> p) {
        mParams = p;
        return this;
    }

    @Override
    protected void build() {
        StringBuilder sb= new StringBuilder(host + url);
        Request.Builder builder = new Request.Builder()
                .tag(tag)
                .get();
        if (mHeader != null)
            for (String key : mHeader.keySet()) {
                builder.addHeader(key, mHeader.get(key));
            }
        if (mParams != null) {
            sb.append("?");
            for (String key : mParams.keySet()) {
                sb.append(key).append("=").append(mParams.get(key)).append("&");
            }
            sb.deleteCharAt(sb.length() - 1);
        }
        builder.url(sb.toString());
        request = builder.build();

    }


}
