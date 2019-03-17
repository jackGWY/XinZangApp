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

public class SaveRegist implements Callable<String> {
    private String userName;
    private String userPassword;
    private String phone;
    private String userType;

    public SaveRegist(String userName, String userPassword, String phone, String userType) {
        this.userName = userName;
        this.userPassword = userPassword;
        this.phone = phone;
        this.userType = userType;
    }



    @Override
    public String call() throws Exception {
        String result="";
        String url = MyURL.SERVER +"/login/register?phone="+phone
                +"&uname="+userName+"&regpass="+userPassword+"&userType="+userType;
        try {
            System.out.println("url:" + url);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            JsonObject jsonObject = (JsonObject) new JsonParser().parse(responseData);
            result = jsonObject.get("result").getAsString();
            System.out.println("result:"+result);
//            Gson gson = new Gson();
//            count = gson.fromJson(responseData, new TypeToken<String>(){}.getType());
//            System.out.println("count:"+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
