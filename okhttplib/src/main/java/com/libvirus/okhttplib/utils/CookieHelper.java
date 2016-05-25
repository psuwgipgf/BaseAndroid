package com.libvirus.okhttplib.utils;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class CookieHelper implements Interceptor {

	public CookieInterface mCookieOpter;

	@Override
	public Response intercept(Chain chain) throws IOException {
		Request request = chain.request();
		Request newRequest = showRequest(request);
		Response response = chain.proceed(newRequest);
		showResponse(response);
		return response;
	}

	private Request showRequest(Request request) {
		if (mCookieOpter != null) {
			return request.newBuilder()
					.addHeader(mCookieOpter.getKey(), mCookieOpter.getCookie())
					.build();
		}
		return request;

	}

	private void showResponse(Response response) {
		if(mCookieOpter!=null){
			String cookie = response.header(mCookieOpter.getKey());
			mCookieOpter.saveCookie(cookie);
		}
	}

}
