package com.guo.http;

import java.io.IOException;
import java.io.Serializable;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by guo_w on 2018/12/19.
 */

public class saveDialogQuestionTitle implements Runnable{
    private String url = MyURL.SERVER+"/consult/saveMessageTitle";
    private String messageTitle;
    private String userName;

    public saveDialogQuestionTitle(String messageTitle, String userName) {
        this.messageTitle = messageTitle;
        this.userName = userName;
    }


    @Override
    public void run() {
        url = url + "?title=" + messageTitle +"&owner="+userName ;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
