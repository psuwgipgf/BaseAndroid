package com.libvirus.okhttplib.request;

import android.text.TextUtils;

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
    public Map<String, String> mParams;

    protected abstract void build();
    protected String buildUrl(){
        if(TextUtils.isEmpty(url)){
            throw new IllegalArgumentException("url not can Empty");
        }
        StringBuilder sb=new StringBuilder(host);
        sb.append(url);
        if(mParams==null){
            return sb.toString();
        }else{
            if(!url.endsWith("?")){
                sb.append("?");
            }
        }
        for(String item:mParams.keySet()){
            sb.append(item);
            sb.append("=");
            sb.append(mParams.get(item));
            sb.append("&");
        }
        sb.deleteCharAt(sb.length()-1);
        return sb.toString();
    }

    public boolean cancel() {
        if (call != null && !call.isCanceled()) {
            call.cancel();
            return true;
        }
        return false;
    }

    private void executive() {
        if (request == null) {
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
