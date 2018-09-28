package com.example.demo.entity;

import java.io.Serializable;

/**
 * 描述:
 * test000
 *
 * @author a4994
 * @create 2018-09-18 11:34
 */

@SuppressWarnings("serial")
public class UserInfo implements Serializable {
    private	String userid;
    private String department;
    private String position;
    private String mobile;
    private String gender;
    private String email;
    public String getUserid() {
        return userid;
    }
    public void setUserid(String userid) {
        this.userid = userid;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }
    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public String getMobile() {
        return mobile;
    }
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    public String getGender() {
        return gender;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public UserInfo(String userid, String department, String position, String mobile, String gender, String email) {
        super();
        this.userid = userid;
        this.department = department;
        this.position = position;
        this.mobile = mobile;
        this.gender = gender;
        this.email = email;
    }
    public UserInfo() {
        super();
        // TODO Auto-generated constructor stub
    }
    @Override
    public String toString() {
        return "UserInfo [userid=" + userid + ", department=" + department + ", position=" + position + ", mobile="
                + mobile + ", gender=" + gender + ", email=" + email + "]";
    }

}
