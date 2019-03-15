package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.Feelings;
import com.guo.beans.diary;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/3/14.
 */

public class getFeelings implements Callable<List<Feelings>> {

    private String userName;
    public getFeelings(String userName) {
        this.userName = userName;
    }

    @Override
    public List<Feelings> call() throws Exception {
        List<Feelings> feelingList = null;
        String url = MyURL.SERVER+"/feeling/getFeelings";
        url = url + "?userName=" + userName;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            System.out.println("responseData:"+responseData);
            JsonParser parser = new JsonParser();
            JsonArray jsonArray = parser.parse(responseData).getAsJsonArray();
            System.out.println("jsonArray.toString():"+jsonArray.toString());

            Gson gson = new Gson();

            ArrayList<Feelings> feelingsArrayListList = new ArrayList<>();
            //加强for循环遍历JsonArray
            for (JsonElement element : jsonArray) {
                //使用GSON，直接转成Bean对象

                Feelings feelings = gson.fromJson(element, Feelings.class);
                feelingsArrayListList.add(feelings);
            }
            for (Feelings d : feelingsArrayListList) {
                System.out.println("toString方法："+d.toString());
            }


            List<Feelings> feelingList1 = gson.fromJson(responseData, new TypeToken<List<Feelings>>(){}.getType());
            for (Feelings d : feelingList1) {
                System.out.println(d.toString());
            }
//            if (jsonArray.size()>0) {
//                for(int i=0;i<jsonArray.size();i++) {
//                    JSONObject jsonObject = jsonArray.getAsJsonObject();
//                }
//            }
            feelingList = feelingList1;
        } catch (Exception e){
            e.printStackTrace();
        }
        return feelingList;
    }
}
