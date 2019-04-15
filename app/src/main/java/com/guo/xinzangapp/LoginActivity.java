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
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

//import com.example.slope.androiddriver.shared.SharedPreferencesManager;

import com.guo.beans.MessageBoard;
import com.guo.http.HttpRequestor;
import com.guo.http.MyURL;
import com.guo.http.saveDiary;
import com.guo.http.saveUserNamePassword;
import com.guo.util.SharedPreferencesManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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

    @BindView(R.id.radio_login)
    RadioGroup mRg1;
    String userType = "patient";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        pref = PreferenceManager.getDefaultSharedPreferences(this);

        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                userType = (String)radioButton.getText();
                System.out.println("userType" + userType);

            }
        });
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
                String uname=loginName.getText().toString().trim();
                String regpass=loginPassword.getText().toString().trim();

                String count = "";
                try {
                    ExecutorService exec = Executors.newCachedThreadPool();
                    Future<String> result = exec.submit(new saveUserNamePassword(uname, regpass,userType));

                    try {
                        count = result.get();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }

                System.out.println("in LoginActivity: count =" + count);
                if (count.equals("1")){
                    editor = pref.edit();
                    editor.putString("userName", uname);
                    editor.putString("userPassword", regpass);
                    editor.putString("userType", userType);
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
                else {
                    Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                            .setAction("服务器或者网络错误", new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(LoginActivity.this, "服务器或者网络错误", Toast.LENGTH_SHORT).show();
                                }
                            }).show();
                    loginName.requestFocus();      // 控件获取焦点
                    return;
                }
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


