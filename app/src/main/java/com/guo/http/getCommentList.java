package com.guo.http;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.MessageBoard;
import com.guo.beans.diary;

import java.util.List;
import java.util.concurrent.Callable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2018/12/20.
 */

public class getCommentList implements Callable<List<MessageBoard>> {
    private String url = MyURL.SERVER+"/consult/getMessageBoardList/";
    private String title;

    public getCommentList(String title) {
        this.title = title;
    }

    @Override
    public List<MessageBoard> call() throws Exception {
        List<MessageBoard> MessageBoardList = null;
        url = url + "?title=" + title;
        System.out.println("url:"+url);
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();

            Gson gson = new Gson();
            List<MessageBoard> MessageBoardList1 = gson.fromJson(responseData, new TypeToken<List<MessageBoard>>(){}.getType());

            MessageBoardList = MessageBoardList1;
            if (MessageBoardList != null) {
                for (MessageBoard messageBoard : MessageBoardList) {
                    System.out.println("getCommmetList:"+messageBoard);
                }
            }
            else {
                System.out.println("MessageBoardList is null!!!!!!!!!!!!!!!!!!!!!!!!");
            }

        } catch (Exception e){
            e.printStackTrace();
        }
        return MessageBoardList;
    }
}
