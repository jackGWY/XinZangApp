package com.guo.xinzangapp.hospital;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.guo.xinzangapp.R;

public class HospitalSwitch2Activity extends AppCompatActivity {

    Button btn_guanhao;
    Button btn_find_hospital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital_switch2);

        btn_find_hospital = (Button) findViewById(R.id.btn_find_hospital);
        btn_find_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://map.baidu.com/mobile/webapp/search/search/qt=con&wd=%E5%8C%BB%E9%99%A2&c=289&newmap=1&from=alamap&tpl=mapdots");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        });
        btn_guanhao = (Button) findViewById(R.id.btn_guahao);
        btn_guanhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://shanghai.guahao.com/");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        });
    }
}
