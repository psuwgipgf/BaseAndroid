package com.psuwgipgf.myapplication.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.psuwgipgf.myapplication.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.psuwgipgf.myapplication.model.tool.TooleKt;

public class QuizActivity extends AppCompatActivity {


    private static final String KEY_INDEX="index";
    @BindView(R.id.true_button)
    public Button button_true;
    @BindView(R.id.false_button)
    public Button button_false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        ButterKnife.bind(this);
        if (savedInstanceState!=null){
            int aa=savedInstanceState.getInt(KEY_INDEX,0);
            TooleKt.toast(this,""+aa);
        }

    }

    @OnClick({R.id.true_button,R.id.false_button})
    public void OnClick(View   view){
        if(view.getId()==R.id.true_button){
            TooleKt.toast(this,getString(R.string.correct_toast));
        }else{
            TooleKt.toast(this,getString(R.string.incorrect_toast));
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("QuizActivity","保存中间状态");
        outState.putInt(KEY_INDEX,12);
    }
}
