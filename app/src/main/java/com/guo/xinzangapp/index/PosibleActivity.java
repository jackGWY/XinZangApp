package com.guo.xinzangapp.index;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.guo.beans.diary;
import com.guo.xinzangapp.R;

public class PosibleActivity extends AppCompatActivity {

    TextView posible;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_posible2);
        posible = (TextView) findViewById(R.id.tv_posible);

        Intent intent=getIntent();//首先要获取bundle
        // 实例化一个Bundle
        Bundle bundle=intent.getExtras();
        String str_posible= (String) bundle.getSerializable("str_posible");
        posible.setText(str_posible);
    }
}
