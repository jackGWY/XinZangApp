package com.guo.xinzangapp.consult;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.guo.beans.UserPatient;
import com.guo.http.GetUserPatients;
import com.guo.xinzangapp.R;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class PatientListActivity extends AppCompatActivity {

    private List<UserPatient> patientList;
    private PatientListAdapter patientListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);

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
            patientList=result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.RecyclerView_patient_list);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        patientListAdapter = new PatientListAdapter(patientList,PatientListActivity.this);
        recyclerView.setAdapter(patientListAdapter);
    }
}
