package com.guo.beans;

/**
 * Created by guo_w on 2019/3/13.
 */

public class Feelings {

    private String userName;
    private String sports;
    private String feeling;
    private String bloodPressure;
    private String heartRate;
    private String remark;

    public Feelings() {
    }

    public Feelings(String userName, String sports, String feeling, String bloodPressure, String heartRate, String remark) {
        this.userName = userName;
        this.sports = sports;
        this.feeling = feeling;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.remark = remark;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSports() {
        return sports;
    }

    public void setSports(String sports) {
        this.sports = sports;
    }

    public String getFeeling() {
        return feeling;
    }

    public void setFeeling(String feeling) {
        this.feeling = feeling;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

