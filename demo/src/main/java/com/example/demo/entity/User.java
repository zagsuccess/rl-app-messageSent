package com.example.demo.entity;

import java.io.Serializable;

/**
 * 描述:
 * test
 *
 * @author a4994
 * @create 2018-09-18 11:32
 */

@SuppressWarnings("serial")
public class User implements Serializable {

    private String id;
    private String password;
    private UserInfo userInfo;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserInfo getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public User() {
        super();
        // TODO Auto-generated constructor stub
    }

    public User(String id, String password, UserInfo userInfo) {
        super();
        this.id = id;
        this.password = password;
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", password=" + password + ", userInfo=" + userInfo.toString() + "]";
    }
}