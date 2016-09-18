package com.psuwgipgf.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.util.Printer;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.psuwgipgf.myapplication.R;
import com.psuwgipgf.myapplication.bean.UserBean;
import com.psuwgipgf.myapplication.presenter.MainActivityPresenter;
import com.psuwgipgf.myapplication.ui.view.CircleImageView;

import java.lang.reflect.Proxy;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.image)
    public ImageView image;
    @Bind(R.id.circleImage)
    public CircleImageView circleImageView;
    @Bind(R.id.text)
    public TextView text;

    private MainActivityPresenter maPresenter = new MainActivityPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(circleImageView);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(image);
        UserBean user=new UserBean();
        user.initBean();
        Toast.makeText(this,user.name+"   "+user.getSex(),Toast.LENGTH_LONG).show();
        user.name="psuwgipgf";
        user.setSex("磊大碕在夺工桍花样百出地");
        user.commit();
        text.setError("asdfasdf");
//        PhoneWindows
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.button2)
    public void onClick1(View v) {
        startActivity(new Intent(this, Main2Activity.class));
    }

}
