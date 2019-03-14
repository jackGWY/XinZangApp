package com.guo.http;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by guo_w on 2019/3/13.
 */

public class saveFeelings implements Runnable {
    private String userName;
    private String sports;
    private String feeling;
    private String bloodPressure;
    private String heartRate;
    private String remark;

    public saveFeelings(String userName, String sports, String feeling, String bloodPressure, String heartRate, String remark) {
        this.userName = userName;
        this.sports = sports;
        this.feeling = feeling;
        this.bloodPressure = bloodPressure;
        this.heartRate = heartRate;
        this.remark = remark;
    }

    @Override
    public void run() {
        final String url = MyURL.SERVER+"/feeling/saveFeeling?userName="+userName+"+&sports="+sports+
                "&feeling="+feeling+"&bloodPressure="+bloodPressure+"&heartRate="+heartRate+"&remark="+remark;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
