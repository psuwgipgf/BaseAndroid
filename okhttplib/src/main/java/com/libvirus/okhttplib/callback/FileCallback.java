package com.libvirus.okhttplib.callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Response;

public abstract class FileCallback extends CallResult {

	private String p;
	private String n;
	private File file;

	public FileCallback(String path, String fileName) {
		p = path;
		n = fileName;
	}

	@Override
	public void onResponse(Call call, Response response) throws IOException {
		if (downLoad(response, p, n)) {
			result(file);
		}
	}

	public abstract void result(File file);

	public abstract void progress(int p);

	private boolean downLoad(Response resp, String path, String fileName) {
		if (resp == null) {
			return false;
		}
		InputStream is = null;
		FileOutputStream fos = null;
		try {
			is = resp.body().byteStream();
			byte[] buf = new byte[2048];
			file = new File(path);
			if (!file.exists()) {
				return false;
			}
			fos = new FileOutputStream(new File(path + "/" + fileName));
			int len = 0;
			double factor = Float.parseFloat(resp.header("Content-Length"));
			double conutLength = 0;
			int progressInt = 0;
			while ((len = is.read(buf)) != -1) {
				fos.write(buf, 0, len);
				conutLength += len;
				int tem = (int) Math.floor(conutLength * 100 / factor);
				if (progressInt == tem) {
					continue;
				}
				progressInt = tem;
				progress(tem);
			}
			return true;
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {

			}
		}
		return false;
	}
}
