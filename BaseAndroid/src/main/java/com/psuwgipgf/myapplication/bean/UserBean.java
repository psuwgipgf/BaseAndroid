package com.psuwgipgf.myapplication.bean;

import com.psuwgipgf.myapplication.model.sp.SharedPreferencesUtil.SharedPreBean;

/**
 * Created by libvirus on 16-8-8.
 */
public class UserBean extends SharedPreBean {

    public String name;
    public String password;

    private int age;
    private String sex;

    @Override
    protected String getPrivateSharePreFileName() {
        return name;
    }

    @Override
    protected String getPublicSharePreFileName() {
        return "Enviroment";
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
