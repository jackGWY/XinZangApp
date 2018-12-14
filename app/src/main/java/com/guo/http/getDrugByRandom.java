package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.drugInfo;

import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2018/12/14. getDrugByRandom
 */

public class getDrugByRandom implements Callable<drugInfo> {
    private String a1;

    public getDrugByRandom(String a1) {
        this.a1 = a1;
    }

    @Override
    public drugInfo call() throws Exception {

        drugInfo dInfo = null;
        try {
            OkHttpClient client = new OkHttpClient();
            String url = MyURL.SERVER+"/drug/getDrugInfoByRandom?a1="+a1;
            System.out.println("url:"+url);
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Gson gson = new Gson();
            drugInfo d = gson.fromJson(responseData, new TypeToken<drugInfo>(){}.getType());
            System.out.println(d.getA1()+d.getA2());
            dInfo = d;
        } catch (Exception e){
            e.printStackTrace();
        }
        return dInfo;
    }
}

