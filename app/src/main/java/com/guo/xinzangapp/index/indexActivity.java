package com.guo.xinzangapp.index;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.guo.http.HttpRequestor;
import com.guo.xinzangapp.R;

import org.json.JSONObject;

public class indexActivity extends AppCompatActivity {



    @BindView(R.id.age)
    EditText age;
    @BindView(R.id.sex)
    EditText sex;
    @BindView(R.id.cp)
    EditText cp;
    @BindView(R.id.trestbps)
    EditText trestbps;
    @BindView(R.id.chol)
    EditText chol;
    @BindView(R.id.fbs)
    EditText fbs;
    @BindView(R.id.restecg)
    EditText restecg;
    @BindView(R.id.thalach)
    EditText thalach;
    @BindView(R.id.exang)
    EditText exang;
    @BindView(R.id.oldpeak)
    EditText oldpeak;
    @BindView(R.id.slop)
    EditText slop;
    @BindView(R.id.ca)
    EditText ca;
    @BindView(R.id.thal)
    EditText thal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.prediction)
    public void onClick(final View view) {
        hideKeyboard();//隐藏键盘
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");
        new Thread(new Runnable() {
            @Override
            public void run() {
                String age2 = age.getText().toString().trim();
                String sex2=sex.getText().toString().trim();
                String cp2=cp.getText().toString().trim();
                String trestbps2=trestbps.getText().toString().trim();
                String chol2=chol.getText().toString().trim();
                String fbs2=fbs.getText().toString().trim();
                String restecg2=restecg.getText().toString().trim();
                String thalach2=thalach.getText().toString().trim();
                String exang2=exang.getText().toString().trim();
                String oldpeak2=oldpeak.getText().toString().trim();
                String slop2=slop.getText().toString().trim();
                String ca2=ca.getText().toString().trim();
                String thal2=thal.getText().toString().trim();

                String url = "http://192.168.123.226:8080/heart/zhibiao/saveInput?userName="+userName+"&age="+age2+"&sex="+sex2+"&cp="+cp2+"&trestbps="+trestbps2+"&chol="+chol2+"&fbs="+fbs2+"&restecg="+restecg2+"&thalach="+thalach2+"&exang="+exang2+"&oldpeak="+oldpeak2+"&slop="+slop2+"&ca="+ca2+"&thal="+thal2;
                try {
                    String jsonString=new HttpRequestor().doGet(url);
                    JSONObject jsonObject = new JSONObject(jsonString);
                    final String result = jsonObject.getString("result");

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
