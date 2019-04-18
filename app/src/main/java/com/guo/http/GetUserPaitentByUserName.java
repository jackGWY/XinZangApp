package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.UserPatient;

import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/4/16.
 */

public class GetUserPaitentByUserName implements Callable<UserPatient> {

    private String userName;
    private String url = MyURL.SERVER+"/user/getUserPatientByUserName";

    public GetUserPaitentByUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public UserPatient call() throws Exception {
        UserPatient userPatient = new UserPatient();
        url = url + "?userName="+userName;

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            Gson gson = new Gson();
            UserPatient userPatient1 = gson.fromJson(responseData, new TypeToken<UserPatient>(){}.getType());
            System.out.println(userPatient1.toString());
            userPatient = userPatient1;
        } catch (Exception  e) {
            e.printStackTrace();
        }

        return userPatient;
    }
}
