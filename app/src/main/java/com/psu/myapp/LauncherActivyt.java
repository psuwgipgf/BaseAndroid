package com.psu.myapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class LauncherActivyt extends Activity {

    private final int SIZE=100;
    private ListView list;
    private Context mContext;
    private List<String> mList;
    @Override @SuppressWarnings("NewApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window=getWindow();
//        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.parseColor("#33ff00aa"));
//        ViewGroup mContentView= (ViewGroup) findViewById(Window.ID_ANDROID_CONTENT);
//        View mChildView=mContentView.getChildAt(0);
//        if(mChildView!=null){
//            ViewCompat.setFitsSystemWindows(mChildView,false);
//        }
        mContext=this;
        setContentView(R.layout.activity_launcher_activyt);
        list=(ListView)findViewById(R.id.list);
        initList();
        list.setAdapter(new MyAdapter());

    }

    private void initList(){
        String[] list={"ActivityAnimaction","ActivityImage","MainActivity",
                "ViewPageActivity","PropertyActivity","Observable"

        };
        mList= Arrays.asList(list);
    }
    class MyAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int position) {
            return mList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=View.inflate(mContext,R.layout.launcher_activity_item,null);
            Button bu=(Button)view.findViewById(R.id.button);
            final String activity=mList.get(position);
            bu.setText(activity);
            final Integer i=Color.parseColor("#ff00dd");
            bu.setBackgroundColor(i);
            bu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent in =new Intent(activity);
                    startActivity(in);
                }
            });
            return view;
        }
    }
}
