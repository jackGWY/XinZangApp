package com.guo.xinzangapp.index;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guo.xinzangapp.BarChart.BarChart;
import com.guo.xinzangapp.BarChart.BarChartActivity;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.heartrate.HeartRateActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class IndexSwitchActivity extends AppCompatActivity {

    @BindView(R.id.index_history)
    Button index_history;
    @BindView(R.id.index_input)
    Button index_input;
    @BindView(R.id.btn_heart_predict)
    Button btn_heart_predict;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index_switch);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.index_history,R.id.index_input,R.id.btn_heart_predict})
    public void OnClick (View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.index_input:
                intent = new Intent(IndexSwitchActivity.this,indexActivity.class);
                startActivity(intent);
                break;
            case R.id.index_history:
                intent = new Intent(IndexSwitchActivity.this,BarChartActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_heart_predict:
                intent = new Intent(IndexSwitchActivity.this,HeartRateActivity.class);
                startActivity(intent);
                break;
        }
    }
}
