package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.Feelings;
import com.guo.beans.HeartNews;
import com.guo.beans.News;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2019/5/16.
 */

public class GetHeartNews implements Callable<List<HeartNews>> {

    public GetHeartNews() {
    }

    @Override
    public List<HeartNews> call() throws Exception {

        List<HeartNews> newList = new ArrayList<>();
        String url = MyURL.SERVER+"/news/getNews";
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = okHttpClient.newCall(request).execute();
            String responseData = response.body().string();
            System.out.println("responseData:"+responseData);

            Gson gson = new Gson();
            newList=gson.fromJson(responseData, new TypeToken<List<HeartNews>>(){}.getType());
            for(HeartNews news:newList){
                System.out.println(news.getTitle());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newList;
    }
}
