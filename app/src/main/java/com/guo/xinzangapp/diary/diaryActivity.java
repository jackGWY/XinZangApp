package com.guo.xinzangapp.diary;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.guo.http.HttpRequestor;
import com.guo.http.MyURL;
import com.guo.xinzangapp.R;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class diaryActivity extends AppCompatActivity {

    @BindView(R.id.date)
    EditText date;
    @BindView(R.id.reason)
    EditText reason;
    @BindView(R.id.hospital)
    EditText hospital;
    @BindView(R.id.drug_used)
    EditText drug_used;
    @BindView(R.id.submit)
    Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.submit})
    public void OnClick(final View view) {
        switch (view.getId()) {
            case R.id.submit:
                String date2=date.getText().toString().trim();
                String reason2=reason.getText().toString().trim();
                String hospital2=hospital.getText().toString().trim();
                String drug_used2=drug_used.getText().toString().trim();

                SharedPreferences pref;
                SharedPreferences.Editor editor;
                pref = PreferenceManager.getDefaultSharedPreferences(this);
                final String userName = pref.getString("userName","");

                final String url = MyURL.SERVER+"/drug/saveDiary?time="+date2+"+&reason="+reason2+"&drugUsed="+drug_used2+"&hospital="+hospital2+"&userName="+userName;

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            String jsonString=new HttpRequestor().doGet(url);
                            JSONObject jsonObject = new JSONObject(jsonString);
                            final String result = jsonObject.getString("result");
                            Log.d("result",result);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                Intent intent = new Intent(diaryActivity.this,diaryListActivity.class);
                startActivity(intent);
                break;
        }
    }
}
