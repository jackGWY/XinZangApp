package com.guo.xinzangapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.drugInfo;
import com.guo.http.GetDrugInfo;
import com.guo.xinzangapp.medicine.DrugAdapter;
import com.guo.xinzangapp.medicine.bMedicine1Activity;
import com.guo.xinzangapp.medicine.bMedicine2Activity;
import com.guo.xinzangapp.medicineArticle.medicineAticle1Activity;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class medicineActivity extends AppCompatActivity {
    private List<drugInfo> DrugList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<drugInfo>> result = exec.submit(new GetDrugInfo());
        try {
            DrugList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (drugInfo dInfo : DrugList) {
            Log.d("medicineActivity","a1:"+dInfo.getA1());
        }
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        DrugAdapter drugAdapter = new DrugAdapter(DrugList,medicineActivity.this);
        recyclerView.setAdapter(drugAdapter);
        //setListener();
    }

}
