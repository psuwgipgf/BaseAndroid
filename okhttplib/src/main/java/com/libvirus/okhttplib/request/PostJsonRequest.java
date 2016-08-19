package com.libvirus.okhttplib.request;

import java.util.Map;

import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

/**
 * Created by psu on 2016/4/16.
 */
public class PostJsonRequest extends OkHttpRequest {

	private static final MediaType JSON = MediaType
			.parse("application/json; charset=utf-8");
	private Request.Builder builder;
	private MultipartBody.Builder BodyBuilder = new MultipartBody.Builder();

	public PostJsonRequest() {
		builder = new Request.Builder();
	}

	public PostJsonRequest setOkHttpClient(OkHttpClient p) {
		mOkHttpClient = p;
		return this;
	}

	public PostJsonRequest host(String u) {
		host = u;
		return this;
	}

	public PostJsonRequest url(String u) {
		url = u;
		return this;
	}

	public PostJsonRequest tag(String u) {
		builder.tag(u);
		return this;
	}

	public PostJsonRequest addHeader(String k, String v) {
		builder.addHeader(k, v);
		return this;
	}

	public PostJsonRequest setHeader(Map<String, String> p) {
		if (p != null) {
			for (String key : p.keySet()) {
				builder.header(key, p.get(key));
			}
		}
		return this;
	}

	public PostJsonRequest addParam(String v) {
		BodyBuilder.addPart(RequestBody.create(JSON, v));
		return this;
	}

	public PostJsonRequest setParam(Map<String, String> p) {
		JSONObject obj = new JSONObject(p);
		String json = "{}";
		if (obj != null) {
			json = obj.toString();
		}
		addParam(json);
		return this;
	}

	@Override
	protected void build() {
		builder.url(host + url).post(BodyBuilder.build());
		request = builder.build();
	}
}
