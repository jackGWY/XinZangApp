package com.guo.xinzangapp.doctors;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.guo.beans.UserPatient;
import com.guo.http.GetUserPatients;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.consult.PatientListActivity;
import com.guo.xinzangapp.consult.PatientListAdapter;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class DoctorsPatientListActivity extends AppCompatActivity {

    private List<UserPatient> patientList;
    private DoctorPatientListAdapter doctorPatientListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_patient_list);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");
        final String userType = pref.getString("userType","");

        Intent intent = getIntent();
//从intent取出bundle
        Bundle bundle = intent.getBundleExtra("Message");
//获取数据
        String fromWhere = bundle.getString("fromWhere");// 得到feeling,binglidan,data

        ExecutorService exec = Executors.newCachedThreadPool();
        System.out.println("userType........................"+userType);
        Future<List<UserPatient>> result = exec.submit(new GetUserPatients("patient"));
        try {
            patientList=result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_doctorsPatientList);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        doctorPatientListAdapter = new DoctorPatientListAdapter(patientList,DoctorsPatientListActivity.this,fromWhere);
        recyclerView.setAdapter(doctorPatientListAdapter);
    }
}
