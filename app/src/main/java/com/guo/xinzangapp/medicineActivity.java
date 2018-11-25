package com.guo.xinzangapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.drugInfo;
import com.guo.http.GetDrugInfo;
import com.guo.xinzangapp.medicine.bMedicine1Activity;
import com.guo.xinzangapp.medicine.bMedicine2Activity;
import com.guo.xinzangapp.medicineArticle.medicineAticle1Activity;

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

    private TextView bMedicine1,bMedicine2,artical1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<List<drugInfo>> result = exec.submit(new GetDrugInfo());
        List<drugInfo> durgInfoList =null;
        try {
            durgInfoList = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        for (drugInfo dInfo : durgInfoList) {
            Log.d("medicineActivity","a1:"+dInfo.getA1());
        }

        bMedicine1 = (TextView) findViewById(R.id.bMedicine1);
        bMedicine2 = (TextView) findViewById(R.id.bMedicine2);

        artical1= (TextView)findViewById(R.id.artical_1);
        bMedicine1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medicineActivity.this,bMedicine1Activity.class));
            }
        });

        bMedicine2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(medicineActivity.this,bMedicine2Activity.class));
            }
        });
        setListener();
    }

    private void setListener(){
        OnClick onClick = new OnClick();
        artical1.setOnClickListener(onClick);
    }

    private class OnClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            Intent intent=null;
                switch(v.getId()){
                    case R.id.artical_1:
                        intent=new Intent(medicineActivity.this,medicineAticle1Activity.class);
                        break;
                }
            startActivity(intent);
        }
    }
}
