package com.psuwgipgf.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.psuwgipgf.myapplication.R;
import com.psuwgipgf.myapplication.bean.UserBean;
import com.psuwgipgf.myapplication.presenter.MainActivityPresenter;
import com.psuwgipgf.myapplication.ui.view.CircleImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

;

public class MainActivity extends BaseActivity {

    @BindView(R.id.image)
    public ImageView image;
    @BindView(R.id.circleImage)
    public CircleImageView circleImageView;
    @BindView(R.id.text)
    public TextView text;

    private MainActivityPresenter maPresenter = new MainActivityPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(circleImageView);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into( image);
        UserBean user=new UserBean();
        user.initBean();
        Toast.makeText(this,user.name+"   "+user.getSex(),Toast.LENGTH_LONG).show();
        user.name="psuwgipgf";
        user.setSex("磊大碕在夺工桍花样百出地");
        user.commit();
//        Looper.loop();
//        text.setError("asdfasdf");
//        PhoneWindows
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @OnClick(R.id.button2)
    public void onClick1(View v) {
        startActivity(new Intent(this, QuizActivity.class));
//        Toast.makeText(this,"asdfasdf",Toast.LENGTH_LONG).show();
    }

}
