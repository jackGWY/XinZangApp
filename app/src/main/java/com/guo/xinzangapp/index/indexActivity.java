package com.guo.xinzangapp.index;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.guo.http.HttpRequestor;
import com.guo.http.MyURL;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.homeActivity;

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

        int posibility = 50;
        int age3=30;
        int cp3=1 ;
        int trestbps3=90;
        int fbs3=110;

        final String age2 = age.getText().toString().trim();
        final String sex2=sex.getText().toString().trim();
        final String cp2=cp.getText().toString().trim();
        final String trestbps2=trestbps.getText().toString().trim();
        final String chol2=chol.getText().toString().trim();
        final String fbs2=fbs.getText().toString().trim();
        final String restecg2=restecg.getText().toString().trim();
        final String thalach2=thalach.getText().toString().trim();
        final String exang2=exang.getText().toString().trim();
        final String oldpeak2=oldpeak.getText().toString().trim();
        final String slop2=slop.getText().toString().trim();
        final String ca2=ca.getText().toString().trim();
        final String thal2=thal.getText().toString().trim();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String url = MyURL.SERVER+"/zhibiao/saveInput?userName="+userName+"&age="+age2+"&sex="+sex2+"&cp="+cp2+"&trestbps="+trestbps2+"&chol="+chol2+"&fbs="+fbs2+"&restecg="+restecg2+"&thalach="+thalach2+"&exang="+exang2+"&oldpeak="+oldpeak2+"&slop="+slop2+"&ca="+ca2+"&thal="+thal2;
                try {
                    String jsonString=new HttpRequestor().doGet(url);
                    JSONObject jsonObject = new JSONObject(jsonString);
                    final String result = jsonObject.getString("result");

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        try{
            age3=Integer.parseInt(age2);
            cp3 = Integer.parseInt(cp2);
            trestbps3= Integer.parseInt(trestbps2);
            fbs3=Integer.parseInt(fbs2);

            if(age3>50 && cp3==1 && (trestbps3>160 || trestbps3<60) && fbs3>120){
                posibility=(int)(1+Math.random()*(30-1+1))+10;
            }
            else {
                posibility=20-(int)(1+Math.random()*(20-1+1));
            }
            String str_posible = posibility + "%";
            Bundle bundle = new Bundle();
            bundle.putSerializable("str_posible",str_posible);
            Intent intent = new Intent(indexActivity.this,PosibleActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(indexActivity.this,"请填写数字！", Toast.LENGTH_LONG).show();
        }

//        if(age3>50 && cp3==1 && (trestbps3>160 || trestbps3<60) && fbs3>120){
//            posibility=(int)(1+Math.random()*(49-1+1))+50;
//        }
//        else {
//            posibility=20-(int)(1+Math.random()*(20-1+1));
//        }
//        String str_posible = posibility + "%";
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("str_posible",str_posible);
//        Intent intent = new Intent(indexActivity.this,PosibleActivity.class);
//        intent.putExtras(bundle);
//        startActivity(intent);
    }
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
}
