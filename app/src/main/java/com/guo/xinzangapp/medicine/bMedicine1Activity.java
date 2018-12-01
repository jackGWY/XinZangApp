package com.guo.xinzangapp.medicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.guo.beans.drugInfo;
import com.guo.xinzangapp.R;

public class bMedicine1Activity extends AppCompatActivity {

    private TextView a1,a2,a3,a4,a5,a6;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_b_medicine1);
        Intent intent=getIntent();
        // 实例化一个Bundle
        Bundle bundle=intent.getExtras();
        //获取里面的Persion里面的数据
        drugInfo dInfo= (drugInfo) bundle.getSerializable("drugInfo");
        a1 = (TextView) findViewById(R.id.drugDetail_a1);
        a2 = (TextView) findViewById(R.id.drugDetail_a2);
        a3 = (TextView) findViewById(R.id.drugDetail_a3);
        a4 = (TextView) findViewById(R.id.drugDetail_a4);
        a5 = (TextView) findViewById(R.id.drugDetail_a5);
        a6 = (TextView) findViewById(R.id.drugDetail_a6);

        a1.setText(dInfo.getA1());
        a2.setText(dInfo.getA2());
        a3.setText(dInfo.getA3());
        a4.setText(dInfo.getA4());
        a5.setText(dInfo.getA5());
        a6.setText(dInfo.getA6());
    }
}
