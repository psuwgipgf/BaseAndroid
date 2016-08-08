package com.psuwgipgf.myapplication.model.image;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

/**
 * Created by psu on 2016/5/11.
 */
public class GlideConfig {

	public void init(Context context) {

	}

	public static void load(Context c, String url, ImageView view) {
		Glide.with(c).load(url).asBitmap().into(view);
	}
}
