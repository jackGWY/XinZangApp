package com.guo.xinzangapp.consult;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.guo.beans.MessageTitle;
import com.guo.beans.UserPatient;
import com.guo.http.GetMessageTitleList;
import com.guo.http.GetUserPatients;
import com.guo.xinzangapp.R;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;

public class FindActivity extends AppCompatActivity {


    List<UserPatient> doctorList;
    FindAdapter findAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");
        final String userType = pref.getString("userType","");

        ExecutorService exec = Executors.newCachedThreadPool();
        System.out.println("userType........................"+userType);
        Future<List<UserPatient>> result = null;
        if(userType.equals("patient")){
            result = exec.submit(new GetUserPatients("doctor"));
        }
        else {
            result = exec.submit(new GetUserPatients("patient"));
        }
//        Future<List<UserPatient>> result = exec.submit(new GetUserPatients(userType));
        try {
            doctorList=result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_find_doctor);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        findAdapter = new FindAdapter(doctorList,userName,FindActivity.this,userType);
        recyclerView.setAdapter(findAdapter);
    }
}
