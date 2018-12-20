package com.guo.xinzangapp.consult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.guo.beans.MessageBoard;
import com.guo.beans.MessageTitle;
import com.guo.beans.diary;
import com.guo.beans.drugInfo;
import com.guo.http.GetDiaryList;
import com.guo.http.getCommentList;
import com.guo.xinzangapp.R;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MessageBoardDetailActivity extends AppCompatActivity {

    private List<MessageBoard> MessageBoardList;
    private TextView title;
    private TextView titleOwner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_board_detail);

        Intent intent=getIntent();
        // 实例化一个Bundle
        Bundle bundle=intent.getExtras();
        //获取里面的Persion里面的数据
        MessageTitle mTitle= (MessageTitle) bundle.getSerializable("messageTitle");
        title = findViewById(R.id.question_title);
        titleOwner = findViewById(R.id.title_user);
        title.setText(mTitle.getTitle());
        titleOwner.setText(mTitle.getOwner());
        ExecutorService exec = Executors.newCachedThreadPool();
        String title = mTitle.getTitle();
        Future<List<MessageBoard>> result = exec.submit(new getCommentList(title));
        try {
            MessageBoardList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_question_discuss);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageDetailAdapter messageDetailAdapter = new MessageDetailAdapter(MessageBoardList,MessageBoardDetailActivity.this);
        recyclerView.setAdapter(messageDetailAdapter);
    }
}
