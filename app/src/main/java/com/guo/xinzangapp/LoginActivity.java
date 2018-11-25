package com.guo.xinzangapp;

//package com.example.slope.androiddriver;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.slope.androiddriver.shared.SharedPreferencesManager;

import com.guo.http.HttpRequestor;
import com.guo.http.MyURL;
import com.guo.util.SharedPreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
//import com.example.slope.androiddriver.http.MyURL;

import static android.R.attr.id;

/**
 * Created by guoweiye on 2018/11/11.
 */
public class LoginActivity extends AppCompatActivity {
    @BindView(R.id.login_name)
    EditText loginName;
    @BindView(R.id.login_layout_name)
    TextInputLayout loginLayoutName;
    @BindView(R.id.login_password)
    EditText loginPassword;
    @BindView(R.id.login_layout_password)
    TextInputLayout loginLayoutPassword;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.login_registered)
    TextView loginRegistered;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
    }


    @OnClick({R.id.btn_login, R.id.login_registered})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                hideKeyboard();//隐藏键盘
                if (loginName.length()==0){
                    Snackbar.make(view,"错误",Snackbar.LENGTH_INDEFINITE)
                            .setAction("请填写账号",  new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(LoginActivity.this, "请填写账号", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    loginName.requestFocus();      // 控件获取焦点
                    return;                     // 结束函数的执行
                }
                if (loginPassword.length()==0){
                    Snackbar.make(view,"错误",Snackbar.LENGTH_INDEFINITE)
                            .setAction("请填写密码", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(LoginActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    loginPassword.requestFocus();       // 控件获取焦点
                    return;                     // 结束函数的执行
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String uname=loginName.getText().toString().trim();
                        String regpass=loginPassword.getText().toString().trim();
                        String url = "http://192.168.123.226:8080/heart/login/doLogin?uname="+uname+"&regpass="+regpass;
                        try {
                            String jsonString = new HttpRequestor().doGet(url);
                            System.out.println("jsonString:"+jsonString);
                            JSONObject jsonObject = new JSONObject(jsonString);
                            String count=jsonObject.getString("count");
                            if (count.equals("1")){
                                editor = pref.edit();
                                editor.putString("userName", uname);
                                editor.putString("userPassword", regpass);
                                editor.apply();
                                startActivity(new Intent(LoginActivity.this,homeActivity.class));
                                //finish();
                            }
                            else if (count.equals("0")){
                                Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                                        .setAction("用户名或密码错误", new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                Toast.makeText(LoginActivity.this, "用户名或密码错误", Toast.LENGTH_SHORT).show();
                                            }
                                        }).show();
                                loginName.requestFocus();      // 控件获取焦点
                                return;
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
//                RequestParams params=new RequestParams(MyURL.MY_SERVWE_LOGIN);
//                params.addQueryStringParameter("uname",loginName.getText().toString().trim());
//                params.addQueryStringParameter("upass",loginPassword.getText().toString().trim());

                break;
            case R.id.login_registered:
                startActivity(new Intent(this,RegisteredActivity.class));
                finish();
                break;
        }


    }

    /**
     * 隐藏键盘
     */
    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }


}


