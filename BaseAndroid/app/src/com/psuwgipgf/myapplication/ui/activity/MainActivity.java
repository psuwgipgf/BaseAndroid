package com.psuwgipgf.myapplication.ui.activity;

import org.elearning.app.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.bumptech.glide.Glide;
import com.psuwgipgf.myapplication.model.ModelManager;
import com.psuwgipgf.myapplication.presenter.MainActivityPresenter;
import com.psuwgipgf.myapplication.ui.view.CircleImageView;

public class MainActivity extends BaseActivity {

	@Bind(R.id.text)
	public TextView text;
	@Bind(R.id.image)
	public ImageView image;
	@Bind(R.id.circleImage)
	public CircleImageView circleImageView;

	private MainActivityPresenter maPresenter = new MainActivityPresenter();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		Glide.with(this)
				.load("http://kkchat.wolianxi.com/attach/userimg/2016/04/2016-04-28/"
						+ "20160428171612614427.png")
				.error(R.drawable.asdlfjei).into(circleImageView);
		ModelManager.imageLoad(this, "http://i.imgur.com/DvpvklR.png", image);

	}

	@OnClick(R.id.button2)
	public void onClick(View v) {
		maPresenter.setTextView(text);
	}

	@OnClick(R.id.button1)
	public void onClick1(View v) {
		startActivity(new Intent(this, Main2Activity.class));
	}

}
