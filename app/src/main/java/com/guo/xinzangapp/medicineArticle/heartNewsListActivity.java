package com.guo.xinzangapp.medicineArticle;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.guo.beans.HeartNews;
import com.guo.beans.News;
import com.guo.http.GetHeartNews;
import com.guo.http.getNews;
import com.guo.xinzangapp.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class heartNewsListActivity extends AppCompatActivity {

    private List<HeartNews> newsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_news_list);

        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<List<HeartNews>> result = executorService.submit(new GetHeartNews());

        try {
            newsList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_heart_news);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        HeartNewsAdapter newsAdapter = new HeartNewsAdapter(heartNewsListActivity.this,newsList);
        recyclerView.setAdapter(newsAdapter);
    }
}
