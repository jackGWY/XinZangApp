package com.guo.beans;

/**
 * Created by guo_w on 2019/3/17.
 */


import java.io.Serializable;

public class UserPatient implements Serializable{
    private String userName;
    private String userPassword;
    private String phone;
    private String userType;

    @Override
    public String toString() {
        return "UserPatient{" +
                "userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", phone='" + phone + '\'' +
                ", userType='" + userType + '\'' +
                '}';
    }

    public UserPatient(String userName, String userPassword, String phone, String userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.phone = phone;
        this.userType = userType;
    }

    public UserPatient() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }
}
