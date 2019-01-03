package com.guo.http;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by guo_w on 2019/1/3.
 */

public class saveDiary implements Runnable{
    private String date;
    private String reason;
    private String drugUsed;
    private String hospital;
    private String userName;

    public saveDiary(String date, String reason, String drugUsed, String hospital, String userName) {
        this.date = date;
        this.reason = reason;
        this.drugUsed = drugUsed;
        this.hospital = hospital;
        this.userName = userName;
    }

    @Override
    public void run() {
        final String url = MyURL.SERVER+"/drug/saveDiary?time="+date+"+&reason="+reason+"&drugUsed="+drugUsed+"&hospital="+hospital+"&userName="+userName;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
