package com.guo.http;


import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/4/16.
 */

public class UpdateUserInfo implements Callable<String> {
    private String originName;
    private String userName;
    private String userPassword;
    private String phone;

    private String url = MyURL.SERVER+"/user/updateUserPatient";

    public UpdateUserInfo(String originName, String userName, String userPassword, String phone) {
        this.originName = originName;
        this.userName = userName;
        this.userPassword = userPassword;
        this.phone = phone;
    }


//    @Override
//    public String run()  {
//        System.out.println("in UpdateUserINfo.java");
//        String count = null;
//        //http://192.168.123.226:8080/heart/user/updateUserPatient?originName=1&userName=1&userPassword=1&phone=1786354122
//        url=url+"?userName="+userName+"originName="+originName+"userPassword="+userPassword+"phone="+phone;
//        try {
//            System.out.println("url:" + url);
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(url).build();
//            try {
//                client.newCall(request).execute();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return count;
//    }

    @Override
    public String call() throws Exception {
        String count = "";
        //http://192.168.123.226:8080/heart/user/updateUserPatient?originName=1&userName=1&userPassword=1&phone=999999
        url=url+"?userName="+userName+"&originName="+originName+"&userPassword="+userPassword+"&phone="+phone;
        try {
//            System.out.println("url:" + url);
//            OkHttpClient client = new OkHttpClient();
//            Request request = new Request.Builder().url(url).build();
//            Response response = client.newCall(request).execute();
//            String responseData = response.body().string();
//            System.out.println("responseData:"+ responseData);
//
//            JsonObject jsonObject = (JsonObject) new JsonParser().parse(responseData);
//            count = jsonObject.get("count").getAsString();
//            System.out.println("count after jsonObjec.get:" + count);
            System.out.println("url:" + url);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            JsonObject jsonObject = (JsonObject) new JsonParser().parse(responseData);
            count = jsonObject.get("count").getAsString();
            System.out.println("result:"+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("count:"+count);
        return count;
    }
}
