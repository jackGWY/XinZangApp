package com.guo.xinzangapp.diary;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.guo.beans.diary;
import com.guo.xinzangapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DiaryDetailActivity extends AppCompatActivity {

    @BindView(R.id.my_time)
    TextView my_time;
    @BindView(R.id.diary_reason)
    TextView diary_reason;
    @BindView(R.id.diary_drug_used)
    TextView diary_drug_used;
    @BindView(R.id.diary_hospital)
    TextView diary_hospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_detail);
        ButterKnife.bind(this);
        Intent intent=getIntent();//首先要获取bundle
        // 实例化一个Bundle
        Bundle bundle=intent.getExtras();
        diary d= (diary) bundle.getSerializable("diary");
        my_time.setText(d.getTime());
        diary_reason.setText(d.getReason());
        diary_drug_used.setText(d.getDrugUsed());
        diary_hospital.setText(d.getHospital());
    }

}
