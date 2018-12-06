package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.diary;

import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2018/12/6.
 */

public class GetDiaryList implements Callable<List<diary>> {
    private String url = MyURL.SERVER+"/drug/getdiaryByUserName";
    private String userName;

    public GetDiaryList(String userName) {
        this.userName = userName;
    }

    @Override
    public List<diary> call() throws Exception {
        List<diary> diaryList = null;
        url = url + "?userName=" + userName;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            Gson gson = new Gson();
            List<diary> drugList1 = gson.fromJson(responseData, new TypeToken<List<diary>>(){}.getType());
            for (diary d : drugList1) {
                System.out.println(d.getHospital());
            }
            diaryList = drugList1;
        } catch (Exception e){
            e.printStackTrace();
        }
        return diaryList;
    }
}
