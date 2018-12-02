package com.guo.http;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.drugInfo;
import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2018/11/11.
 */

public class GetDrugInfo implements Callable<List<drugInfo>> {
    private static final String url = MyURL.SERVER+"/drug/getDrugInfo";

    public List<drugInfo> getDrugInfoFromServer() {
        List<drugInfo> List2 = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            Gson gson = new Gson();
            List<drugInfo> drugList1 = gson.fromJson(responseData, new TypeToken<List<drugInfo>>(){}.getType());
            for (drugInfo d : drugList1) {
                System.out.println(d.getA1());
            }
            List2 = drugList1;
        } catch (Exception e){
            e.printStackTrace();
        }
        return List2;
    }

    @Override
    public List<drugInfo> call() throws Exception {

        List<drugInfo> List2 = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            Gson gson = new Gson();
            List<drugInfo> drugList1 = gson.fromJson(responseData, new TypeToken<List<drugInfo>>(){}.getType());
            for (drugInfo d : drugList1) {
                System.out.println(d.getA1());
            }
            List2 = drugList1;
        } catch (Exception e){
            e.printStackTrace();
        }
        return List2;
    }
}
