package com.libvirus.okhttplib.request;

import java.io.File;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by psu on 2016/4/29.
 */
public class PostFileRequest extends OkHttpRequest {

    private MultipartBody.Builder BodyBuilder = new MultipartBody.Builder();
    private Request.Builder builder;
    private MediaType type;

    public PostFileRequest() {
        builder = new Request.Builder();
    }


    public PostFileRequest setType(MediaType t) {
        type = t;
        return this;
    }

    public PostFileRequest setOkHttpClient(OkHttpClient p) {
        mOkHttpClient = p;
        return this;
    }

    public PostFileRequest host(String u) {
        host = u;
        return this;
    }

    public PostFileRequest url(String u) {
        url = u;
        return this;
    }

    public PostFileRequest tag(String u) {
        builder.tag(u);
        return this;
    }

    public PostFileRequest addHeader(String k, String v) {
        builder.addHeader(k, v);
        return this;
    }

    public PostFileRequest setHeader(Map<String, String> p) {
        if (p != null) {
            for (String key : p.keySet()) {
                builder.header(key, p.get(key));
            }
        }
        return this;
    }

    public PostFileRequest addParam(String k, Object v) {
        if (File.class.isInstance(v)) {
            File value = (File) v;
            RequestBody body = RequestBody.create(
                    MediaType.parse(guessMimeType(value.getName())), value);
            BodyBuilder.addFormDataPart(k, value.getName(), body);
        } else {
            BodyBuilder.addFormDataPart(k, v.toString());
        }

        return this;
    }

    public PostFileRequest setParam(Map<String, Object> p) {
        if (p != null) {
            for (String key : p.keySet()) {
                addParam(key, p.get(key));
            }
        }

        return this;
    }

    public PostFileRequest addParams(String key, List<Object> p) {
        if (p != null) {
            for (Object item : p) {
                addParam(key, item);
            }
        }
        return this;
    }

    @Override
    protected void build() {
        if (type == null) {
            BodyBuilder.setType(MultipartBody.FORM);
        } else {
            BodyBuilder.setType(type);
        }
        builder.url(host + url)
                .post(BodyBuilder.build());
        request = builder.build();
    }


    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
