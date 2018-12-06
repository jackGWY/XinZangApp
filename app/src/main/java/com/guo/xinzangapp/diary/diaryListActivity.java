package com.guo.xinzangapp.diary;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.guo.beans.diary;
import com.guo.beans.drugInfo;
import com.guo.http.GetDiaryList;
import com.guo.http.GetDrugInfo;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.medicineArticle.NewsAdapter;
import com.guo.xinzangapp.medicineArticle.newListActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.ButterKnife;

public class diaryListActivity extends AppCompatActivity {

    private List<diary> diaryList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");

        ButterKnife.bind(this);
        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<diary>> result = exec.submit(new GetDiaryList(userName));
        try {
            diaryList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

//        for(diary d : diaryList) {
//            System.out.println("diary:"+d);
//        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_diary_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DiaryAdapter diaryAdapter = new DiaryAdapter(diaryList,diaryListActivity.this);
        recyclerView.setAdapter(diaryAdapter);
    }
}
