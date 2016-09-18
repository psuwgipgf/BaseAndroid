package com.libvirus.okhttplib.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Request.Builder;
import okhttp3.Response;
import okhttp3.ResponseBody;

import org.json.JSONException;
import org.json.JSONObject;

import com.libvirus.okhttplib.OkHttpManager;

public class OAuthInterceptor implements Interceptor {

    private String token;
    private static OkHttpClient OAuth = new OkHttpClient();
    private CookieInterface cookie;

    public OAuthInterceptor() {
        cookie = new CookieInterface() {
            @Override
            public String getCookie() {
                return null;
            }

            @Override
            public void saveCookie(String cookie) {

            }
        };
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        // 设置Token
        Builder builder = request.newBuilder();
        token = cookie.getCookie();
        builder.header("Authorization", token);
        Response response = chain.proceed(builder.build());
        // Token无效，返回401
        if (!verificationToken(response)) {
            // 刷新Token
            reFreshToken();
            // 设置Token,重新请求
            response = chain.proceed(builder.header("Authorization",
                    cookie.getCookie()).build());
            return response;
        }
        return response;
    }

    private synchronized void reFreshToken() {
        String currentToken = cookie.getCookie();
        if (currentToken != null && currentToken.equals(token)) {
            Response response = OkHttpManager
                    .postRequest()
                    .setOkHttpClient(OAuth)
                    .url("")
                    .addHeader("Authorization",
                            "asdf").exec();
            try {
                String tem = response.body().string();
                tem = tem.substring(1);
                JSONObject obj = new JSONObject(tem);
                String newToken = obj.optString("token_type") + " "
                        + obj.optString("access_token");
                cookie.saveCookie(newToken);
            } catch (JSONException | IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean verificationToken(Response response) {
        try {
            long length = response.body().contentLength();
            if (length > 0) {
                ResponseBody body = response.peekBody(length);
                String tem = body.string();
                tem = tem.substring(1);
                JSONObject obj = new JSONObject(tem);
                if (obj.optInt("statusCode", 0) == 401) {
                    return false;
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return true;
    }

}
