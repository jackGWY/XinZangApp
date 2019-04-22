package com.guo.xinzangapp;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.guo.beans.UserPatient;
import com.guo.http.GetUserPaitentByUserName;
import com.guo.http.UpdateUserInfo;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MyActivity extends AppCompatActivity {

    private EditText et_userName;
    private EditText et_password;
    private EditText et_phone;
    private Button btn_submit;

    String userName="";

    private UserPatient userPatient2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);


        et_password = (EditText) findViewById(R.id.et_userpassword);
        et_phone = (EditText) findViewById(R.id.et_phone);
        btn_submit=(Button) findViewById(R.id.btn_update);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        userName = pref.getString("userName","");

        ExecutorService exec = Executors.newCachedThreadPool();
        Future<UserPatient> result =null;

        result =exec.submit(new GetUserPaitentByUserName(userName));
        try {
            userPatient2 = result.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        et_userName = (EditText) findViewById(R.id.et_username);
        et_userName.setText(userPatient2.getUserName());
        et_userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(et_userName);
            }
        });

        et_password = (EditText) findViewById(R.id.et_userpassword);
        et_password.setText(userPatient2.getUserPassword());
        et_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(et_password);
            }
        });

        et_phone = (EditText) findViewById(R.id.et_phone);
        et_phone.setText(userPatient2.getPhone());
        et_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog(et_phone);
            }
        });

        btn_submit=(Button)findViewById(R.id.btn_update);
        btn_submit.setOnClickListener(new View.OnClickListener() {
            String count="";
            @Override
            public void onClick(View v) {
                try {
                    ExecutorService exec = Executors.newCachedThreadPool();
                    Future<String> result =exec.submit(new UpdateUserInfo(userName,et_userName.getText().toString(),
                            et_password.getText().toString(),et_phone.getText().toString()));

                    try {
                        count = result.get();
                        System.out.println("in myavtivity,count:"+count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (ExecutionException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e){
                    e.printStackTrace();
                }


                Log.d("MyActivity:","修改成功");
//                startActivity(new Intent(this,RegisteredActivity.class));
            }
        });

    }

    //对话框
    protected void dialog(final EditText editText) {
        final View layout = View.inflate(MyActivity.this, R.layout.message_title_dialog,
                null);
        AlertDialog.Builder builder = new AlertDialog.Builder(MyActivity.this);
        builder.setTitle("请修改");
        builder.setIcon(R.drawable.rating_bar_bg);
//        builder.setMessage("文档：" + listDetail.getOriginname());
        builder.setView(layout);
        builder.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //获得dialog 中 edittext的值
                EditText edt_message_title = (EditText) layout.findViewById(R.id.edt_message_title);
                String edtMessageTitle = edt_message_title.getText().toString();
                Toast.makeText(MyActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                Log.d("MyActivity:",edtMessageTitle);
                editText.setText(edtMessageTitle);
                //获取请求的参数
            }
        });

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.create().show();
    }
}
