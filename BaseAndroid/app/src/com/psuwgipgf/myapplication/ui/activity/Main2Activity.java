package com.psuwgipgf.myapplication.ui.activity;

import org.elearning.app.R;

import android.os.Bundle;
import android.view.View;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.psuwgipgf.myapplication.eventbus.RxBus;
import com.psuwgipgf.myapplication.eventbus.RxBusType;

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
}
