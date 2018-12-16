package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.MessageTitle;
import com.guo.beans.diary;

import java.io.Serializable;
import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2018/12/16.
 */

public class GetMessageTitleList implements Callable<List<MessageTitle>> {
    private String url = MyURL.SERVER+"/consult/getMessageTitleList";

    @Override
    public List<MessageTitle> call() throws Exception {
        List<MessageTitle> messageTitleList = null;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            Gson gson = new Gson();
            List<MessageTitle> messageTitleList2 = gson.fromJson(responseData, new TypeToken<List<MessageTitle>>(){}.getType());
            for (MessageTitle messageTitle : messageTitleList2) {
                System.out.println(messageTitle.getTitle());
            }
            messageTitleList=messageTitleList2;
        } catch (Exception  e) {
            e.printStackTrace();
        }
        return messageTitleList;
    }
}
