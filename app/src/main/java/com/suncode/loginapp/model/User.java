package com.suncode.loginapp.model;

public class User {
    private String name;
    private String uId;
    private int tag;

    public User(String name, String uId, int tag) {
        this.name = name;
        this.uId = uId;
        this.tag = tag;
    }

    public User() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public int getTag() {
        return tag;
    }

    public void setTag(int tag) {
        this.tag = tag;
    }
}
