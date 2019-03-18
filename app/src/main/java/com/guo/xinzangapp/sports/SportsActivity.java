package com.guo.xinzangapp.sports;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guo.beans.Feelings;
import com.guo.beans.diary;
import com.guo.http.GetDiaryList;
import com.guo.http.saveFeelings;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.homeActivity;
import com.guo.xinzangapp.medicine.drugSwitchActivity;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SportsActivity extends AppCompatActivity {

    @BindView(R.id.btn_sports)
    Button btn_sports;
    @BindView(R.id.btn_feeling)
    Button btn_feeling;
    @BindView(R.id.blood_pressure)
    EditText blood_pressure;
    @BindView(R.id.heart_rate)
    EditText heart_rate;
    @BindView(R.id.edt_remark)
    EditText edt_remark;
    @BindView(R.id.upload)
    Button upload;
    String resultString="";
    String feelingString="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sports);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_sports,R.id.btn_feeling,R.id.upload})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btn_sports:
                final String[] array4=new String[]{"散步","跑步","游泳","太极拳","瑜伽"};
                boolean[] isSelected=new boolean[]{false,false,false,false,false};
                AlertDialog.Builder builder4=new AlertDialog.Builder(SportsActivity.this);
                builder4.setTitle("选择运动类型").setMultiChoiceItems(array4, isSelected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        resultString+=array4[which] + " ";
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(resultString);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                }).show();
                break;
            case R.id.btn_feeling:
                final String[] feeling=new String[]{"胸闷","气促","心慌","头晕","晕厥"};
                boolean[] Selected=new boolean[]{false,false,false,false,false};
                AlertDialog.Builder builder2=new AlertDialog.Builder(SportsActivity.this);
                builder2.setTitle("您的感受").setMultiChoiceItems(feeling, Selected, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        feelingString+=feeling[which] + " ";
                    }
                }).setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        System.out.println(feelingString);
                    }
                }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //
                    }
                }).show();
                break;
            case R.id.upload:
                String bloodPressure=blood_pressure.getText().toString().trim();
                String heartRate=heart_rate.getText().toString().trim();
                String edtRemark = edt_remark.getText().toString().trim();

                SharedPreferences pref;
                pref = PreferenceManager.getDefaultSharedPreferences(this);
                final String userName = pref.getString("userName","");

                saveFeelings feelings = new saveFeelings(userName,resultString,feelingString,
                        bloodPressure,heartRate,edtRemark);
                ExecutorService exec = Executors.newCachedThreadPool();
                exec.submit(feelings);
                Toast.makeText(SportsActivity.this,"保存成功", Toast.LENGTH_SHORT).show();

                Bundle bundle = new Bundle();
//                bundle.putSerializable("fromPatientListAdapter","nothing");
//                bundle.putSerializable("patientName","nothing");
                bundle.putString("fromPatientListAdapter","nothing");
                bundle.putString("patientName","nothing");

                Intent intent = new Intent(SportsActivity.this, FeelingListActivity.class);
//                intent.putExtras(bundle);
                intent.putExtra("Message",bundle);
                startActivity(intent);
                break;
        }
    }
}
