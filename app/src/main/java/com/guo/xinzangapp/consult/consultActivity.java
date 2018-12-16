package com.guo.xinzangapp.consult;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.guo.beans.MessageTitle;
import com.guo.beans.diary;
import com.guo.http.GetMessageTitleList;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.diary.DiaryAdapter;
import com.guo.xinzangapp.diary.diaryListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.ButterKnife;

public class consultActivity extends AppCompatActivity {

    List<MessageTitle> messageTitleList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult);
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");

        ButterKnife.bind(this);
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<MessageTitle>> result = exec.submit(new GetMessageTitleList());
        try {
            messageTitleList=result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (MessageTitle messageTitle : messageTitleList) {
            Log.d("consultActivity:",messageTitle.getTitle());
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_message_board);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        MessageTitleAdapter messageTitleAdapter = new MessageTitleAdapter(messageTitleList,consultActivity.this);
        recyclerView.setAdapter(messageTitleAdapter);
    }
}
