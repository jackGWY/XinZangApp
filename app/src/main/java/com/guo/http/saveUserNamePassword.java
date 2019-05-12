package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.MessageBoard;

import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/1/8.
 */

public class saveUserNamePassword implements Callable<String>{

    String uname;
    String regpass;
    String userType;

    public saveUserNamePassword(String uname, String regpass, String userType) {
        this.uname = uname;
        this.regpass = regpass;
        this.userType = userType;
    }

    String url = MyURL.SERVER;

    @Override
    public String call() throws Exception {
        String count = "";
        url = url + "/login/doLogin?uname="+uname+"&regpass="+regpass+"&userType="+userType;
        try {
            System.out.println("url:" + url);
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            System.out.println("responseData:"+ responseData);

            JsonObject jsonObject = (JsonObject) new JsonParser().parse(responseData);
            count = jsonObject.get("count").getAsString();
            System.out.println("count after jsonObjec.get:" + count);
//            Gson gson = new Gson();
//            count = gson.fromJson(responseData, new TypeToken<String>(){}.getType());
//            System.out.println("count:"+count);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("count:"+count);
        return count;
    }
}
