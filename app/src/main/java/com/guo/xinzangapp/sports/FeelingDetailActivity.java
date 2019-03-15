package com.guo.xinzangapp.sports;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.guo.beans.Feelings;
import com.guo.beans.diary;
import com.guo.xinzangapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FeelingDetailActivity extends AppCompatActivity {

    @BindView(R.id.tv_sports)
    TextView sports;
    @BindView(R.id.tv_feeling)
    TextView feeling;
    @BindView(R.id.tv_heart_rate)
    TextView heartRate;
    @BindView(R.id.tv_blood_pressure)
    TextView bloodPressure;
    @BindView(R.id.tv_remark)
    TextView remark;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feeling_detail);

        ButterKnife.bind(this);
        Intent intent=getIntent();//首先要获取bundle
        // 实例化一个Bundle
        Bundle bundle=intent.getExtras();
        Feelings feelings= (Feelings) bundle.getSerializable("feelings");
        sports.setText(feelings.getSports());
        feeling.setText(feelings.getFeeling());
        heartRate.setText(feelings.getHeartRate());
        bloodPressure.setText(feelings.getBloodPressure());
        remark.setText(feelings.getRemark());
    }
}
