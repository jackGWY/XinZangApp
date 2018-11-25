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
import com.guo.xinzangapp.medicine.bMedicine1Activity;
import com.guo.xinzangapp.medicine.bMedicine2Activity;
import com.guo.xinzangapp.medicineArticle.medicineAticle1Activity;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class medicineActivity extends AppCompatActivity {

    private TextView bMedicine1,bMedicine2,artical1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);

        sendRequestWithOkHttp();

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

    //通过线程获取服务器数据
    private List<drugInfo> sendRequestWithOkHttp() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url("http://192.168.123.226:8080/heart/drug/getDrugInfo").build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();

                    Gson gson = new Gson();
                    List<drugInfo> drugList = gson.fromJson(responseData, new TypeToken<List<drugInfo>>(){}.getType());
                    for (drugInfo dInfo : drugList) {
                        Log.d("medicineActivity","a1:"+dInfo.getA1());
                    }

                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        return null;
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
