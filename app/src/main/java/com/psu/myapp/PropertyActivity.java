package com.psu.myapp;

import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

public class PropertyActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_property);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image:
                propertyAnimation(v);
                break;
            case R.id.button:
                showreflex();
                break;
        }
    }

    private void propertyAnimation(final View v) {
//        PropertyValuesHolder pvhX=PropertyValuesHolder.ofFloat("alpha",1f,0,1f,0);
//        PropertyValuesHolder pvhY=PropertyValuesHolder.ofFloat("scaleX",1f,0,1f);
//        PropertyValuesHolder pvhZ=PropertyValuesHolder.ofFloat("scaleY",1f,0,1f);
//        ObjectAnimator.ofPropertyValuesHolder(v,pvhX,pvhY,pvhZ).setDuration(500).start();
        ValueAnimator animator=new ValueAnimator();
        animator.setDuration(1000);
        animator.setObjectValues(new PointF(0,0));
        animator.setTarget(v);
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF pf=new PointF();
                pf.x = 200 * fraction * 3;
                pf.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return pf;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pf=(PointF)animation.getAnimatedValue();
                v.setX(pf.x);
                v.setY(pf.y);
            }
        });
        animator.start();

    }

    private void showreflex() {
        Class c = View.class;
        Method[] arr = c.getDeclaredMethods();
        FileOutputStream fos=null;
        try {
            fos = openFileOutput("property", MODE_PRIVATE);
            for (Method method : arr) {
                if (method.getName().startsWith("get") || method.getName().startsWith("set")) {
                    fos.write(method.getName().getBytes());
                    fos.write("\n".getBytes());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}




