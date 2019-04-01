package com.guo.xinzangapp;

//package com.example.slope.androiddriver;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.guo.http.SaveRegist;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Slope on 2016/9/11.
 */
public class RegisteredActivity extends AppCompatActivity {
    @BindView(R.id.register_name)
    EditText registerName;
    @BindView(R.id.register_layout_name)
    TextInputLayout registerLayoutName;
    @BindView(R.id.register_phone)
    EditText registerPhone;
    @BindView(R.id.register_layout_phone)
    TextInputLayout registerLayoutPhone;
    @BindView(R.id.register_password)
    EditText registerPassword;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    @BindView(R.id.register_layout_password)
    TextInputLayout registerLayoutPassword;
    @BindView(R.id.btn_register)
    Button btnRegister;
    @BindView(R.id.radioButton)
    RadioGroup mRg1;
    String userType = "patient";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                userType = (String)radioButton.getText();
                System.out.println("userType" + userType);
            }
        });
    }

    @OnClick(R.id.btn_register)
    public void onClick(final View view) {
        hideKeyboard();//隐藏键盘

        if (!confirmPassword.getText().toString().trim().equals(registerPassword.getText().toString().trim())) {

            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("两次填写密码不一致", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "两次填写密码不一致", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            confirmPassword.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }

        if (confirmPassword.length() == 0) {

            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请再次填写确认密码", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "请确认密码", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            confirmPassword.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }

        if (registerPhone.length() == 0) {
            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请填写手机号", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "请填写手机号", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerPhone.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }
        if (registerName.length() == 0) {
            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请填写用户名", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "请填写用户名", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerName.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }
        if (registerPassword.length() == 0) {
            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("请填写密码", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "请填写密码", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerPassword.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }

        String phone = registerPhone.getText().toString().trim();
        String uname = registerName.getText().toString().trim();
        String regpass=registerPassword.getText().toString().trim();

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<String> result = exec.submit(new SaveRegist(uname,regpass,phone,userType));
        String count = "";
        try {
            count=result.get();
            System.out.println("in RegisterActivity count:"+count);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if(count.equals("")) {
            Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
                    .setAction("注册失败，遇到网络问题，重新操作", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Toast.makeText(RegisteredActivity.this, "注册失败，遇到网络问题，重新操作", Toast.LENGTH_SHORT).show();
                        }
                    }).show();
            registerName.requestFocus();      // 控件获取焦点
            return;                     // 结束函数的执行
        }
        else if(count.equals("0")) {
            Toast.makeText(RegisteredActivity.this, "注册成功", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(RegisteredActivity.this, LoginActivity.class));
        }
//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                String phone = registerPhone.getText().toString().trim();
//                String uname = registerName.getText().toString().trim();
//                String regpass=registerPassword.getText().toString().trim();
//
//                String url = MyURL.SERVER+"/login/register?phone="+phone+"&uname="+uname+"&regpass="+regpass+"&userType="+userType;
//                //url = "http://192.168.123.226:8080/heart/login/register?phone=111&uname=zfj&regpass=123456";
//                try {
//                    String jsonString=new HttpRequestor().doGet(url);
//                    JSONObject jsonObject = new JSONObject(jsonString);
//                    final String result = jsonObject.getString("result");
//                    if ("0".equals(jsonObject.getString("result"))) {
//                        startActivity(new Intent(RegisteredActivity.this, LoginActivity.class));
//                        //finish();
//                    } else if ("1".equals(jsonObject.getString("result"))) {
//                        Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
//                                .setAction("该手机已近注册过", new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        Toast.makeText(RegisteredActivity.this, "该手机已近注册过", Toast.LENGTH_SHORT).show();
//                                    }
//                                }).show();
//                        registerPhone.requestFocus();      // 控件获取焦点
//                        return;                     // 结束函数的执行
//                    } else if ("3".equals(jsonObject.getString("result"))) {
//                        Snackbar.make(view, "错误", Snackbar.LENGTH_INDEFINITE)
//                                .setAction("该用户名已经存在", new View.OnClickListener() {
//                                    @Override
//                                    public void onClick(View v) {
//                                        Toast.makeText(RegisteredActivity.this, "该用户名已经存在", Toast.LENGTH_SHORT).show();
//                                    }
//                                }).show();
//                        registerName.requestFocus();      // 控件获取焦点
//                        return;                     // 结束函数的执行
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
        //startActivity(new Intent(RegisteredActivity.this, LoginActivity.class));
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
