package com.guo.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/3/17.
 */

public class saveRelation implements Callable<String>{
    private String patient;
    private String doctor;

    public saveRelation(String patient, String doctor) {
        this.patient = patient;
        this.doctor = doctor;
    }


    @Override
    public String call() throws Exception {
        String count="";
        String url = MyURL.SERVER +"/user/saveRelation?patient="+patient+"&doctor="+doctor;
        try {
            System.out.println("url:" + url);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            JsonObject jsonObject = (JsonObject) new JsonParser().parse(responseData);
            count = jsonObject.get("count").getAsString();
            System.out.println("count:"+count);

//            Gson gson = new Gson();
//            count = gson.fromJson(responseData, new TypeToken<String>(){}.getType());
//            System.out.println("count:"+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
}
