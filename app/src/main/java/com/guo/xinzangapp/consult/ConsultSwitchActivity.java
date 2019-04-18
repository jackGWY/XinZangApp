package com.guo.xinzangapp.consult;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.guo.xinzangapp.R;
import com.guo.xinzangapp.ViewPagerActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsultSwitchActivity extends AppCompatActivity {

    @BindView(R.id.btn_discuss)
    Button dicuss;
//    @BindView(R.id.btn_consult_doctor)
//    Button communicate;
    @BindView(R.id.btn_find_dorctor)
    Button find_doctor;
    @BindView(R.id.btn_call)
    Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consult_switch);
        ButterKnife.bind(this);
    }
    @OnClick({R.id.btn_discuss,R.id.btn_find_dorctor,R.id.btn_call})
    public void onClick(final View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.btn_discuss:
                intent = new Intent(ConsultSwitchActivity.this, consultActivity.class);
                startActivity(intent);
                break;
//            case R.id.btn_consult_doctor:
//                intent = new Intent(ConsultSwitchActivity.this, ViewPagerActivity.class);
//                startActivity(intent);
//                break;
            case R.id.btn_find_dorctor:
                intent = new Intent(ConsultSwitchActivity.this, FindActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_call:
                call("120");
                break;
        }
    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}
