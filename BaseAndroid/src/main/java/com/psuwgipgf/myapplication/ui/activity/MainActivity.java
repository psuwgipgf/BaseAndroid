package com.psuwgipgf.myapplication.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.psuwgipgf.myapplication.R;
import com.psuwgipgf.myapplication.presenter.MainActivityPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Bind(R.id.text)
    public TextView text;

    private MainActivityPresenter maPresenter = new MainActivityPresenter();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
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
