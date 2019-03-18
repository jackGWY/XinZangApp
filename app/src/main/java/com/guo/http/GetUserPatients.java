package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.MessageTitle;
import com.guo.beans.UserPatient;

import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/3/17.
 */

public class GetUserPatients implements Callable<List<UserPatient>> {
    private String url = MyURL.SERVER+"/user/getDoctors";

    private String userType;

    public GetUserPatients(String userType) {
        this.userType = userType;
    }

    @Override
    public List<UserPatient> call() throws Exception {
        List<UserPatient> doctorList = null;
        url = url + "?userType="+userType;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            Gson gson = new Gson();
            List<UserPatient> doctorList2 = gson.fromJson(responseData, new TypeToken<List<UserPatient>>(){}.getType());
            for (UserPatient userPatient : doctorList2) {
                System.out.println(userPatient.toString());
            }
            doctorList=doctorList2;
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return doctorList;
    }
}
