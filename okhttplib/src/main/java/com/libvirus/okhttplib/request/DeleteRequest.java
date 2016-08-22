package com.libvirus.okhttplib.request;

import java.util.List;
import java.util.Map;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by psu on 2016/4/16.
 */
public class DeleteRequest extends OkHttpRequest {

	private Request.Builder builder;
	private FormBody.Builder builderBody = new FormBody.Builder();

	public DeleteRequest() {
		builder = new Request.Builder();
	}

	public DeleteRequest setOkHttpClient(OkHttpClient p) {
		mOkHttpClient = p;
		return this;
	}

	public DeleteRequest host(String u) {
		host = u;
		return this;
	}

	public DeleteRequest url(String u) {
		url = u;
		return this;
	}

	public DeleteRequest tag(String u) {
		builder.tag(u);
		return this;
	}

	public DeleteRequest addHeader(String k, String v) {
		builder.addHeader(k, v);
		return this;
	}

	public DeleteRequest setHeader(Map<String, String> p) {
		if (p != null) {
			for (String key : p.keySet()) {
				builder.header(key, p.get(key));
			}
		}
		return this;
	}

	public DeleteRequest addParam(String k, String v) {
		builderBody.add(k, v);
		return this;
	}

	public DeleteRequest setParam(Map<String, String> p) {
		if (p != null) {
			for (String key : p.keySet()) {
				builderBody.add(key, p.get(key));
			}
		}

		return this;
	}

	public DeleteRequest addParams(String key, List<String> p) {
		if (p != null) {
			for (String item : p) {
				builderBody.add(key, item);
			}
		}
		return this;
	}

	@Override
	protected void build() {
		builder.url(host + url).delete(builderBody.build());
		request = builder.build();
	}
}
