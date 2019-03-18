package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.Chat;
import com.guo.beans.diary;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/3/18.
 */

public class GetChatList implements Callable<List<Chat>>{

    private String patient;
    private String doctor;

    public GetChatList(String patient, String doctor) {
        this.patient = patient;
        this.doctor = doctor;
    }

    @Override
    public List<Chat> call() throws Exception {

        List<Chat> chatList = new ArrayList<>();
        String url = MyURL.SERVER+"/message/get"+"?patient=" + patient+"&doctor=" + doctor;
        System.out.println("url...................................."+url);

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            System.out.println("response...............................:"+response.toString());
            String responseData = response.body().string();
            System.out.println("responseData...............................:"+responseData);
            Gson gson = new Gson();
            List<Chat> chatList1 = gson.fromJson(responseData, new TypeToken<List<Chat>>(){}.getType());
            for (Chat d : chatList1) {
                System.out.println(d.toString());
            }
            chatList = chatList1;
        } catch (Exception e){
            e.printStackTrace();
        }
        return chatList;
    }
}
