package com.libvirus.okhttplib;

import com.libvirus.okhttplib.request.GetRequest;
import com.libvirus.okhttplib.request.PostRequest;
import com.libvirus.okhttplib.utils.LogHelper;

import okhttp3.Call;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;

/**
 * Created by psu on 2016/4/9.
 */
public class OkHttpManager {

    private static OkHttpManager mInstance;
    private volatile OkHttpClient mOkHttpClient;
    public String host;

    public static OkHttpManager getInstace() {
        if (mInstance == null) {
            synchronized (OkHttpManager.class) {
                if (mInstance == null) {
                    mInstance = new OkHttpManager(null);
                }
            }
        }
        return mInstance;
    }

    public OkHttpManager(OkHttpClient okHttpClient) {
        if (okHttpClient != null) {
            mOkHttpClient = okHttpClient;
        } else {
            //默认OkHttpClient;
            mOkHttpClient = new OkHttpClient().newBuilder().build();
        }
    }

    public OkHttpClient getOkHttpClient() {

        return mOkHttpClient;
    }


    public OkHttpManager log(String tag) {
        mOkHttpClient = getOkHttpClient().newBuilder().addInterceptor(new LogHelper(tag)).build();
        return this;
    }

    public OkHttpManager host(String url) {
        host = url;
        return this;
    }

    //取消Call 为空取消所有
    public boolean cancel(String ... tag){
        Dispatcher dispatcher= mOkHttpClient.dispatcher();
        if(tag==null &&tag.length<1){
            dispatcher.cancelAll();
            return true;
        }
        StringBuilder sb=new StringBuilder();
        for(String t:tag){
            sb.append(t);
        }
        String cancelTag=sb.toString();
        for(Call dueCall:dispatcher.queuedCalls()){
            if(cancelTag.startsWith(dueCall.request().tag().toString())){
                dueCall.cancel();
            }
        }
        for(Call runCall:dispatcher.runningCalls()){
            if(cancelTag.startsWith(runCall.request().tag().toString())){
                runCall.cancel();
            }
        }
        return false;
    }

    public static GetRequest getRequest(){
        return new GetRequest();
    }
    public static PostRequest postRequest(){
        return new PostRequest();
    }
}
