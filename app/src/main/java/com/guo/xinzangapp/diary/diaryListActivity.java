package com.guo.xinzangapp.diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

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

import butterknife.BindView;
import butterknife.ButterKnife;

public class diaryListActivity extends AppCompatActivity {

    private List<diary> diaryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_list);

        FloatingActionButton FAdd = (FloatingActionButton)findViewById(R.id.binglidan_add);
        FAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(diaryListActivity.this,diaryActivity.class);
                startActivity(intent);
            }
        });
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");
        final String userType = pref.getString("userType","");
        ButterKnife.bind(this);
        ExecutorService exec = Executors.newCachedThreadPool();

        Intent intent = getIntent();
//从intent取出bundle
        Bundle bundle = intent.getBundleExtra("Message");
//获取数据
        String patientName = bundle.getString("patientName");

        Future<List<diary>> result = null;
        if(userType.equals("patient")){
            result = exec.submit(new GetDiaryList(userName));
        }
        else {
            result = exec.submit(new GetDiaryList(patientName));
        }
        //Future<List<diary>> result = exec.submit(new GetDiaryList(userName));
        try {
            diaryList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(diaryList!=null && diaryList.size()!=0){
            for(diary d : diaryList) {
                System.out.println("diaryListActivity:"+d);
            }
        }
        else {
            diary d = new diary();
            d.setDrugUsed("没有数据");
            d.setHospital("没有数据");
            d.setReason("请添加数据");
            d.setTime("请添加数据");
            d.setUserName(userName);
            diaryList.add(d);
        }



        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_diary_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DiaryAdapter diaryAdapter = new DiaryAdapter(diaryList,diaryListActivity.this);
        recyclerView.setAdapter(diaryAdapter);
    }
}
