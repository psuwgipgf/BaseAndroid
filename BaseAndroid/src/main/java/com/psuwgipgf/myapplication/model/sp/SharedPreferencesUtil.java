package com.psuwgipgf.myapplication.model.sp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by psu on 2016/5/24.
 */

public class SharedPreferencesUtil {

    public static Application app;

    public static void init(Application a) {
        app = a;
    }

    static <B> void setSPToBean(Map<String, Object> params, B bean)
            throws IllegalAccessException, IllegalArgumentException {
        Field[] arrField = bean.getClass().getDeclaredFields();
        for (Field f : arrField) {
            Type type = f.getGenericType();
            Object value = params.get(f.getName());
            if (!params.containsKey(f.getName())) {
                continue;
            }
            if (type.toString().equals("class java.lang.String")) {
                f.set(bean, value);
            } else if (type.toString().equals("int")) {
                f.setInt(bean, (int) value);
            } else if (type.toString().equals("boolean")) {
                f.setBoolean(bean, (boolean) value);
            }
        }
    }

    static <B> void setBeanToSP(B bean, Editor edit)
            throws IllegalAccessException, IllegalArgumentException {
        Field[] arrField = bean.getClass().getDeclaredFields();
        for (Field f : arrField) {
            Type type = f.getGenericType();
            Object value = f.get(bean);
            if (value == null) {
                continue;
            }
            if (type.toString().equals("class java.lang.String")) {
                edit.putString(f.getName(), value.toString());
            } else if (type.toString().equals("int")) {
                edit.putInt(f.getName(), (int) value);
            } else if (type.toString().equals("boolean")) {
                edit.putBoolean(f.getName(), (boolean) value);
            }
        }
    }

    public static <B> void get(String name, B ub) {
        Map<String, Object> params = new HashMap<String, Object>();
        SharedPreferences sp = app.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        params = (Map<String, Object>) sp.getAll();
        try {
            setSPToBean(params, ub);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public static <B> void set(String name, B ub) {
        SharedPreferences sp = app.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        Editor edit = sp.edit();
        try {
            setBeanToSP(ub, edit);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        edit.commit();
    }

}

