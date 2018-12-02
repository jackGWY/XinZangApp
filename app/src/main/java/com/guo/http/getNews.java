package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.News;

import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2018/12/2.
 */

public class getNews implements Callable<List<News>> {
    private static final String url = MyURL.SERVER+"/drug/getNews";
    @Override
    public List<News> call() throws Exception {
        List<News> newList = null;
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            String responseData = response.body().string();

            Gson gson = new Gson();
            newList=gson.fromJson(responseData, new TypeToken<List<News>>(){}.getType());
            for(News news:newList){
                System.out.println(news.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return newList;
    }
}
