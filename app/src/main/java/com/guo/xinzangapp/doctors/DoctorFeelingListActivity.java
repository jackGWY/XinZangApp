package com.guo.xinzangapp.doctors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.guo.beans.Feelings;
import com.guo.http.getFeelings;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.sports.FeelingAdapter;
import com.guo.xinzangapp.sports.FeelingListActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DoctorFeelingListActivity extends AppCompatActivity {

    private List<Feelings> feelingsList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_feeling_list);

        TextView tv_patientName = (TextView) findViewById(R.id.doc_patient_name);

        Intent intent = getIntent();
        //从intent取出bundle
        Bundle bundle = intent.getBundleExtra("Message");
        //获取数据
        String patientName = bundle.getString("patientName");

        tv_patientName.setText(patientName);

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<Feelings>> result =exec.submit(new getFeelings(patientName));

        try {
            feelingsList = result.get();
            if(feelingsList!=null && feelingsList.size()!=0){
                for(Feelings f : feelingsList) {
                    System.out.println(f.toString());
                }
            }
            else {
                Feelings f = new Feelings();
                f.setBloodPressure(0);
                f.setFeeling("没有数据");
                f.setHeartRate(0);
                f.setRemark("没有数据");
                f.setSports("没有数据");
                f.setUserName(patientName);
                feelingsList.add(f);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_doc_feeling_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        FeelingAdapter feelingAdapter = new FeelingAdapter(feelingsList,DoctorFeelingListActivity.this);
        recyclerView.setAdapter(feelingAdapter);
    }
}
