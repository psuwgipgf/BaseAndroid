package com.libvirus.okhttplib.request;

import java.util.LinkedHashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by psu on 2016/4/10.
 */
public class GetRequest extends OkHttpRequest {

    private Request.Builder builder;

    public GetRequest(){
        builder= new Request.Builder()
                .get();
    }
    public GetRequest setOkHttpClient(OkHttpClient p) {
        mOkHttpClient = p;
        return this;
    }

    public GetRequest host(String u) {
        host = u;
        return this;
    }

    public GetRequest url(String u) {
        url = u;
        return this;
    }

    public GetRequest tag(String u) {
        builder.tag(u);
        return this;
    }

    public GetRequest addHeader(String k, String v) {
        builder.addHeader(k, v);
        return this;
    }

    public GetRequest setHeader(Map<String, String> p) {
        if(p!=null){
            for(String key:p.keySet()){
                builder.header(key,p.get(key));
            }
        }
        return this;
    }

    public GetRequest addParams(String k, String v) {
        if (mParams == null) {
            mParams = new LinkedHashMap<>();
        }
        mParams.put(k, v);
        return this;
    }

    public OkHttpRequest setParams(Map<String, String> p) {
        mParams = p;
        return this;
    }

    @Override
    protected void build() {
        StringBuilder sb= new StringBuilder(host + url);
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
