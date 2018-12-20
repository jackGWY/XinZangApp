package com.guo.http;

import com.guo.beans.MessageBoard;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by guo_w on 2018/12/20.
 */

public class saveMessageQA implements Runnable {
    private String url = MyURL.SERVER+"/consult/saveMessageBoard";
    private MessageBoard messageBoard;

    public saveMessageQA(MessageBoard messageBoard) {
        this.messageBoard = messageBoard;
    }

    @Override
    public void run() {
        //consult/saveMessageBoard?topicTitle=这些&toppicOwner=1&comment=发烧&commentOwner=123
        url = url + "?topicTitle=" + messageBoard.getTopicTitle() +
                "&toppicOwner="+messageBoard.getToppicOwner() + "&comment="+messageBoard.getComment()
                +"&commentOwner="+messageBoard.getCommentOwner();

        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        try {
            client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
