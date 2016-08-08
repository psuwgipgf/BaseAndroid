package com.psuwgipgf.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.psuwgipgf.myapplication.R;
import com.psuwgipgf.myapplication.bean.UserBean;
import com.psuwgipgf.myapplication.presenter.MainActivityPresenter;
import com.psuwgipgf.myapplication.ui.view.CircleImageView;
import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

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
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(circleImageView);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(image);
        UserBean user=new UserBean();
        user.name="psuwgipgf";
        user.password="123456";
        user.setAge(10);
        user.setSex("asdfasdf");
        user.commitPrivate();
        user.commitPublic();
    }

    @OnClick(R.id.button2)
    public void onClick(View v) {
        maPresenter.setTextView(text);
    }

    @OnClick(R.id.button1)
    public void onClick1(View v) {
        startActivity(new Intent(this, Main2Activity.class));
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        sendBroadcast(new Intent("Main2Activity"));
    }
}
