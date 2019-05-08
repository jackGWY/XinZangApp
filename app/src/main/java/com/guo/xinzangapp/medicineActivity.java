package com.guo.xinzangapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.guo.beans.MessageTitle;
import com.guo.beans.drugInfo;
import com.guo.http.GetDrugInfo;
import com.guo.http.SaveDrugInfo;
import com.guo.http.saveDialogQuestionTitle;
import com.guo.xinzangapp.consult.consultActivity;
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

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class medicineActivity extends AppCompatActivity {
    private ArrayList<drugInfo> DrugList = new ArrayList<>();
    private DrugAdapter drugAdapter;
    @BindView(R.id.drug_add)
    FloatingActionButton drugAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine);
        ButterKnife.bind(this);
        Intent intent=getIntent();
        // 实例化一个Bundle
        Bundle bundle=intent.getExtras();
        //获取里面的Persion里面的数据
        DrugList= (ArrayList<drugInfo>) bundle.getSerializable("ResDrugList");

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        drugAdapter = new DrugAdapter(DrugList,medicineActivity.this);
        recyclerView.setAdapter(drugAdapter);
        //setListener();
    }
    @OnClick({R.id.drug_add})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.drug_add:
                Log.d("medicineAcitivity","drugAddButtom clicked..");
                SharedPreferences pref;
                SharedPreferences.Editor editor;
                pref = PreferenceManager.getDefaultSharedPreferences(medicineActivity.this);
                final String userName = pref.getString("userName","");
                final String userType = pref.getString("userType","");
                Log.d("consultActivity",userName);
                if(userType.equals("patient")){
                    Toast ss = Toast.makeText(medicineActivity.this, "病人不能添加药物",Toast.LENGTH_LONG);
                    ss.show();
                }
                else {
                    dialog();
                }
                break;
        }
    }
    //对话框
    protected void dialog() {
        final View layout = View.inflate(medicineActivity.this, R.layout.drug_add_dialog,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(medicineActivity.this);
        builder.setTitle("添加药物");
        builder.setIcon(R.drawable.rating_bar_bg);
//        builder.setMessage("文档：" + listDetail.getOriginname());
        builder.setView(layout);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获得dialog 中 edittext的值
                EditText edit_a1 = (EditText) layout.findViewById(R.id.add_a1);
                String a1 = edit_a1.getText().toString();
                EditText edit_a2 = (EditText) layout.findViewById(R.id.add_a2);
                String a2 = edit_a1.getText().toString();
                EditText edit_a3 = (EditText) layout.findViewById(R.id.add_a3);
                String a3 = edit_a1.getText().toString();
                EditText edit_a4 = (EditText) layout.findViewById(R.id.add_a4);
                String a4 = edit_a1.getText().toString();
                EditText edit_a5 = (EditText) layout.findViewById(R.id.add_a5);
                String a5 = edit_a1.getText().toString();
                EditText edit_a6 = (EditText) layout.findViewById(R.id.add_a6);
                String a6 = edit_a1.getText().toString();
                EditText edit_a7 = (EditText) layout.findViewById(R.id.add_a7);
                String a7 = edit_a1.getText().toString();
                Toast.makeText(medicineActivity.this, "添加成功", Toast.LENGTH_SHORT).show();
                Log.d("consultActivity",a1);
                //获取请求的参数
                ExecutorService exec = Executors.newCachedThreadPool();
                exec.submit(new SaveDrugInfo(a1,a2,a3,a4,a5,a6,a7));

                drugInfo dInfo = new drugInfo(a1,a2,a3,a4,a5,a6,a7);
                drugAdapter.addItem(dInfo);
//                MessageTitle messageTitle = new MessageTitle(edtMessageTitle,userName);
//                messageTitleAdapter.addItem(messageTitle);
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }

}
