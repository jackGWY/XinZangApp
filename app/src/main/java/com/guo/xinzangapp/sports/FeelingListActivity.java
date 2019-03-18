package com.guo.xinzangapp.sports;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.guo.beans.Feelings;
import com.guo.beans.diary;
import com.guo.http.GetDiaryList;
import com.guo.http.getFeelings;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.diary.DiaryAdapter;
import com.guo.xinzangapp.diary.diaryListActivity;
import com.guo.xinzangapp.homeActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindInt;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeelingListActivity extends AppCompatActivity {

    @BindView(R.id.history_add)
    FloatingActionButton imageAdd;

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

        Intent intent = getIntent();
        //从intent取出bundle
        Bundle bundle = intent.getBundleExtra("Message");
        //获取数据
        String d = bundle.getString("fromPatientListAdapter");
        String patientName = bundle.getString("patientName");


//        Intent intent=getIntent();
//// 实例化一个Bundle
//        Bundle bundle=intent.getExtras();
////获取里面的Persion里面的数据
//
//        String d= (String) bundle.getSerializable("fromPatientListAdapter");
//        String patientName= (String) bundle.getSerializable("patientName");
        System.out.println("d:"+d);
        System.out.println("patientName:........................"+patientName);

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<Feelings>> result =null;
        if(d.equals("fromPatientListAdapter")){
            if(!(patientName.equals("nothing"))){
                result = exec.submit(new getFeelings(patientName));
            }

        } else {
            result = exec.submit(new getFeelings(userName));
        }

//        Future<List<Feelings>> result = exec.submit(new getFeelings(userName));
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

    @OnClick({R.id.history_add})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.history_add:
                startActivity(new Intent(FeelingListActivity.this, SportsActivity.class));
                break;
        }
    }
}
