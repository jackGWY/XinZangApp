package com.guo.beans;

import java.io.Serializable;
import java.sql.Date;

/**
 * Created by guo_w on 2018/12/6.
 */

public class diary implements Serializable {

    private String time;
    private String reason;
    private String drugUsed;
    private String hospital;
    private String userName;

    public diary() {
    }

    public diary(String time, String reason, String drugUsed, String hospital, String userName) {
        this.time = time;
        this.reason = reason;
        this.drugUsed = drugUsed;
        this.hospital = hospital;
        this.userName = userName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDrugUsed() {
        return drugUsed;
    }

    public void setDrugUsed(String drugUsed) {
        this.drugUsed = drugUsed;
    }

    public String getHospital() {
        return hospital;
    }

    public void setHospital(String hospital) {
        this.hospital = hospital;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
