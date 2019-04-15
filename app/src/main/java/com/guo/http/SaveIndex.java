package com.guo.http;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by guo_w on 2019/4/11.
 */

public class SaveIndex implements Runnable{



    private String userName;
    private int age;
    private String sex;
    private int cp;
    private int trestbps;
    private int chol;
    private int fbs;
    private int restecg;
    private int thalach;
    private int exang;
    private double oldpeak;
    private int slop;
    private int ca;
    private int thal;

    public SaveIndex(String userName, int age, String sex, int cp, int trestbps, int chol, int fbs, int restecg, int thalach, int exang, double oldpeak, int slop, int ca, int thal) {
        this.userName = userName;
        this.age = age;
        this.sex = sex;
        this.cp = cp;
        this.trestbps = trestbps;
        this.chol = chol;
        this.fbs = fbs;
        this.restecg = restecg;
        this.thalach = thalach;
        this.exang = exang;
        this.oldpeak = oldpeak;
        this.slop = slop;
        this.ca = ca;
        this.thal = thal;
    }

    @Override
    public void run() {

        String url = MyURL.SERVER+"/zhibiao/saveInput?userName="+userName+"&age="+age+
                "&sex="+sex+"&cp="+cp+"&trestbps="+trestbps+"&chol="+chol+"&fbs="+fbs+
                "&restecg="+restecg+"&thalach="+thalach+"&exang="+exang+"&oldpeak="+oldpeak+
                "&slop="+slop+"&ca="+ca+"&thal="+thal;

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

