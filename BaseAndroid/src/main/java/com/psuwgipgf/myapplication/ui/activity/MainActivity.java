package com.psuwgipgf.myapplication.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.psuwgipgf.myapplication.R;
import com.psuwgipgf.myapplication.eventbus.RxBus;
import com.psuwgipgf.myapplication.eventbus.RxBusType;
import com.psuwgipgf.myapplication.presenter.MainActivityPresenter;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.functions.Action1;

public class MainActivity extends BaseActivity {

    @Bind(R.id.text)
    public TextView text;

    private MainActivityPresenter maPresenter=new MainActivityPresenter();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void onClick(View v) {
        maPresenter.setTextView(text);
    }

}
