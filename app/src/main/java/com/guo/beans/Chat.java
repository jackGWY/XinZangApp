package com.guo.beans;

/**
 * Created by guo_w on 2019/3/18.
 */

public class Chat {
    private String patient;
    private String message;
    private String doctor;
    private String belong;

    @Override
    public String toString() {
        return "Chat{" +
                "patient='" + patient + '\'' +
                ", message='" + message + '\'' +
                ", doctor='" + doctor + '\'' +
                ", belong='" + belong + '\'' +
                '}';
    }

    public Chat(String patient, String message, String doctor, String belong) {
        this.patient = patient;
        this.message = message;
        this.doctor = doctor;
        this.belong = belong;
    }

    public String getPatient() {
        return patient;
    }

    public void setPatient(String patient) {
        this.patient = patient;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDoctor() {
        return doctor;
    }

    public void setDoctor(String doctor) {
        this.doctor = doctor;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }
}