package com.guo.xinzangapp.sports;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.guo.beans.Feelings;
import com.guo.beans.diary;
import com.guo.http.GetDiaryList;
import com.guo.http.getFeelings;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.diary.DiaryAdapter;
import com.guo.xinzangapp.diary.diaryListActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.ButterKnife;

public class FeelingListActivity extends AppCompatActivity {

    private List<Feelings> feelingsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling_list);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");

        ButterKnife.bind(this);
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<Feelings>> result = exec.submit(new getFeelings(userName));
        try {
            feelingsList = result.get();
            for(Feelings f : feelingsList) {
                System.out.println(f.toString());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_feeling_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FeelingAdapter feelingAdapter = new FeelingAdapter(feelingsList,FeelingListActivity.this);
        recyclerView.setAdapter(feelingAdapter);
    }
}
