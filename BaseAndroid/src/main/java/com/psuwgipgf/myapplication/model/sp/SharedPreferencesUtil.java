package com.psuwgipgf.myapplication.model.sp;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.text.TextUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by psu on 2016/5/24.
 */

public class SharedPreferencesUtil {

    private static final int OPERATING_ALL = Modifier.PUBLIC + Modifier.PRIVATE;//保存所有
    private static final int OPERATING_PUBLIC = Modifier.PUBLIC;//保存公共数据
    private static final int OPERATING_PRIVATE = Modifier.PRIVATE;//保存私有数据


    public static Application app;

    public static void init(Application a) {
        app = a;
    }

    static <B> void setSharedPreToBean(Map<String, Object> params, B bean)
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

    static <B> void setBeanToSharedPre(B bean, Editor PublicEdit, Editor PrivateEdit, int Params)
            throws IllegalAccessException, IllegalArgumentException {
        Field[] arrField = bean.getClass().getDeclaredFields();
        for (Field f : arrField) {
            Editor edit = null;
            if (f.getModifiers() == OPERATING_PUBLIC && Params != OPERATING_PRIVATE) {
                edit = PublicEdit;
            } else if (f.getModifiers() == OPERATING_PRIVATE && Params != OPERATING_PUBLIC) {
                edit = PrivateEdit;
                f.setAccessible(true);
            } else {
                continue;
            }
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
        if (PublicEdit != null) {
            PublicEdit.commit();
        }
        if (PrivateEdit != null) {
            PrivateEdit.commit();
        }

    }

    public static boolean setToBean(String name, SharedPreBean ub) {
        Map<String, Object> params = new HashMap<String, Object>();
        SharedPreferences sp = app.getSharedPreferences(name,
                Context.MODE_PRIVATE);
        params.putAll(sp.getAll());
        try {
            setSharedPreToBean(params, ub);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static boolean setToSharedPre(String name, SharedPreBean ub, int saveType) {
        Editor publicEdit = null;
        Editor privateEdit = null;
        if (saveType != OPERATING_PRIVATE) {
            SharedPreferences sp = app.getSharedPreferences(name,
                    Context.MODE_PRIVATE);
            publicEdit = sp.edit();
        }
        if (saveType != OPERATING_PUBLIC) {
            String privateName = ub.getPrivateSharePreFileName();
            if (!TextUtils.isEmpty(privateName)) {
                privateEdit = app.getSharedPreferences(privateName, Context.MODE_PRIVATE).edit();
            } else {
                saveType = OPERATING_PUBLIC;
            }
        }

        try {
            setBeanToSharedPre(ub, publicEdit, privateEdit, saveType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return false;
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static abstract class SharedPreBean {

        protected abstract String getPrivateSharePreFileName();

        protected abstract String getPublicSharePreFileName();

        public boolean initBean() {
            String name = getPublicSharePreFileName();
            if (!TextUtils.isEmpty(name)) {
                boolean tem1=setToBean(name, this);
                if(!TextUtils.isEmpty(name=getPrivateSharePreFileName())){
                    boolean tem2=setToBean(name,this);
                    if(tem1&&tem2){
                        return true;
                    }
                }
                return tem1 ;
            }
            return false;
        }

        public boolean commitPublic() {
            String name = getPublicSharePreFileName();
            if (!TextUtils.isEmpty(name)) {
                return setToSharedPre(name, this, OPERATING_PUBLIC);
            }
            return false;
        }

        public boolean commitPrivate() {
            String name = getPrivateSharePreFileName();
            if (!TextUtils.isEmpty(name)) {
                return setToSharedPre(name, this, OPERATING_PRIVATE);
            }
            return false;
        }

        public boolean commit() {
            String name = getPublicSharePreFileName();
            if (!TextUtils.isEmpty(name)) {
                return setToSharedPre(name, this, OPERATING_ALL);
            }
            return false;
        }

    }

}

