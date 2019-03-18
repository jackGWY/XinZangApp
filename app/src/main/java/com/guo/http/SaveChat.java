package com.guo.http;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/3/18.
 */

public class SaveChat implements Callable<String> {
    private String patient;
    private String message;
    private String doctor;
    private String belong;

    public SaveChat(String patient, String message, String doctor, String belong) {
        this.patient = patient;
        this.message = message;
        this.doctor = doctor;
        this.belong = belong;
    }

    @Override
    public String call() throws Exception {

        String count="";
        String url = MyURL.SERVER +"/message/save?patient="+patient+"&doctor="+doctor+"&message="+message+"&belong="+belong;
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
