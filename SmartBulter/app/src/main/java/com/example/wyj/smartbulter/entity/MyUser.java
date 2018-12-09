package com.example.wyj.smartbulter.entity;

import cn.bmob.v3.BmobUser;

public class MyUser extends BmobUser {

    private int age;
    private boolean isMale;
    private String desc;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isMale() {
        return isMale;
    }

    public void setMale(boolean male) {
        isMale = male;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
