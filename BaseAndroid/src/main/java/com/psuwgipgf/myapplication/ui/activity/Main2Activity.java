package com.psuwgipgf.myapplication.ui.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.psuwgipgf.myapplication.R;
import com.psuwgipgf.myapplication.eventbus.RxBus;
import com.psuwgipgf.myapplication.eventbus.RxBusType;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Main2Activity extends BaseActivity {



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.quit)
    public void OnCliek(View v){
        RxBus.send(new RxBusType(RxBusType.QUIT));
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
