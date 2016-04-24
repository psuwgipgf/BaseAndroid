package com.libvirus.okhttplib.request;

import com.libvirus.okhttplib.OkHttpManager;
import com.libvirus.okhttplib.callback.CallResult;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by psu on 2016/4/9.
 */
public abstract class OkHttpRequest {

    public OkHttpManager mOkHttpManager = OkHttpManager.getInstace();
    public OkHttpClient mOkHttpClient = mOkHttpManager.getOkHttpClient();
    public String host = mOkHttpManager.host;
    public Request request;
    public Call call;
    public String url;
    public String tag;
    public Map<String, String> mHeader;
    public Map<String, String> mParams;

    public abstract OkHttpRequest host(String u);

    public abstract OkHttpRequest url(String u);

    public abstract OkHttpRequest tag(String u);

    public abstract OkHttpRequest addHeader(String k, String v);

    public abstract OkHttpRequest setHeader(Map<String, String> p);

    public abstract OkHttpRequest addParams(String k, String v);

    public abstract OkHttpRequest setParams(Map<String, String> p);
    protected abstract void build();

    public boolean cancel() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
            return true;
        }
        return false;
    }

    private void executive() {
        if(request==null){
            build();
        }
        call = mOkHttpClient.newCall(request);
    }

    public Response exec() {
        executive();
        try {
            return call.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void exec(CallResult callResult) {
        executive();
        call.enqueue(callResult);
    }

}
