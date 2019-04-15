package com.guo.xinzangapp.index;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guo.http.HttpRequestor;
import com.guo.http.MyURL;
import com.guo.http.SaveIndex;
import com.guo.http.SaveRegist;
import com.guo.xinzangapp.R;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import org.json.JSONObject;

import java.lang.reflect.Field;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class NumberPickerActivity extends AppCompatActivity {

    //年龄
    private EditText edWorkingAge;
    private Button submit_workingAge,submitWorkingAge;
    private PopupWindow popupWindow;
    private NumberPicker numberPicker;
    private View workingAge_view;
    private int workingAge = 0;
    //血压
    private EditText etBloodPressure;
    private Button submit_bloodPressure;
    private PopupWindow popupWindow_blood_pressure;
    private NumberPicker numberPicker_blood_pressure;
    private View blood_pressure_view;
    private int bloodPressure = 70;
    //chol血清胆固醇
    private EditText et_chol;
    private Button submit_chol;
    private PopupWindow popupWindow_chol;
    private NumberPicker numberPicker_chol;
    private View chol_view;
    private int chol = 110;
    //fbs 空腹血糖
    private EditText et_fbs;
    private Button submit_fbs;
    private PopupWindow popupWindow_fbs;
    private NumberPicker numberPicker_fbs;
    private View fbs_view;
    private int fbs = 80;
    //ca 血管数目
    private EditText et_ca;
    private Button submit_ca;
    private PopupWindow popupWindow_ca;
    private NumberPicker numberPicker_ca;
    private View ca_view;
    private int ca = 0;
    //thalach 最大心跳
    private EditText et_thalach;
    private Button submit_thalach;
    private PopupWindow popupWindow_thalach;
    private NumberPicker numberPicker_thalach;
    private View thalach_view;
    private int thalach = 72;
    //oldpeak
    private EditText et_oldpeak;
    private Button submit_oldpeak;
    private PopupWindow popupWindow_oldpeak;
    private NumberPicker numberPicker_1;
    private NumberPicker numberPicker_2;
    private View oldpeak_view;
    private int oldpeak1 = 2;
    private int oldpeak2 = 1;
    private double oldpeak = oldpeak1+oldpeak2 * 0.1;
    //男女sex
    private RadioGroup mRg1;
    private String sex = "男";

    //疼痛类型
    private RadioGroup rg_chest_pain_type;
    private String chest_pain_type;
    private int painType=4;
    //restecg 心电图
    private RadioGroup rg_restecg;
    private String str_restecg;
    private int restecg_int=0;
    //exang 运动诱发心绞痛
    private RadioGroup rg_exang;
    private String str_exang;
    private int exang_int=0;
    //slop 心电图坡度
    private RadioGroup rg_slop;
    private String str_slop;
    private int slop_int=0;
    //thal 缺陷类型
    private RadioGroup rg_thal;
    private String str_thal;
    private int thal_int=0;

    private int posibility = 50;
//    private String str_posibility="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");



        initNumberPicker();
        //预测提交按钮
        submitWorkingAge = (Button) findViewById(R.id.submitWorkingAge);
        submitWorkingAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        String url = MyURL.SERVER+"/zhibiao/saveInput?userName="+userName+"&age="+workingAge+
//                                "&sex="+sex+"&cp="+chest_pain_type+"&trestbps="+bloodPressure+"&chol="+chol+"&fbs="+fbs+
//                                "&restecg="+restecg_int+"&thalach="+thalach+"&exang="+exang_int+"&oldpeak="+oldpeak+
//                                "&slop="+slop_int+"&ca="+ca+"&thal="+thal_int;
//                        try {
//                            String jsonString=new HttpRequestor().doGet(url);
//                            JSONObject jsonObject = new JSONObject(jsonString);
//                            final String result = jsonObject.getString("result");
//
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }).start();

                try{
                    ExecutorService exec = Executors.newCachedThreadPool();
                    SaveIndex saveIndex = new SaveIndex(userName,workingAge,sex,painType,bloodPressure,chol,fbs,restecg_int,thalach,exang_int,oldpeak,slop_int,ca,thal_int);
                    exec.submit(saveIndex);
                } catch (Exception e){
                    e.printStackTrace();
                    System.out.println("saveIndex.................");
                }



                if(workingAge>50 && painType==1 && (bloodPressure>160 || bloodPressure<60) && fbs>120){
                    posibility=(int)(1+Math.random()*(30-1+1))+10;
                }
                else {
                    posibility=20-(int)(1+Math.random()*(20-1+1));
                }
                String str_posible = posibility + "%";
                Bundle bundle = new Bundle();
                bundle.putSerializable("str_posible",str_posible);
                Intent intent = new Intent(NumberPickerActivity.this,PosibleActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        //男女@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        mRg1=(RadioGroup)findViewById(R.id.rg_1);
        mRg1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                sex = radioButton.getText().toString();
//                System.out.println(sex);
                Toast.makeText(NumberPickerActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        // 疼痛类型@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@  rg_chest_pain_type
        rg_chest_pain_type=(RadioGroup)findViewById(R.id.rg_chest_pain_type);
        rg_chest_pain_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                chest_pain_type = radioButton.getText().toString();
                if(chest_pain_type.equals("典型的心绞痛")){
                    painType = 1;
                }
                else if(chest_pain_type.equals("非典型的心绞痛")){
                    painType = 2;
                }
                else if(chest_pain_type.equals("非心绞痛的疼痛")){
                    painType = 3;
                }
                else {
                    painType = 4;
                }
//                System.out.println(painType+"");
                Toast.makeText(NumberPickerActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        // thal 缺陷种类
        rg_thal=(RadioGroup)findViewById(R.id.rg_thal);
        rg_thal.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                str_thal = radioButton.getText().toString();
                if(str_thal.equals("正常(normal)")){
                    thal_int = 0;
                }
                else if(str_thal.equals("可逆缺陷(reversable defect)")){
                    thal_int = 1;
                }
                else {
                    thal_int = 2;
                }

//                System.out.println(painType+"");
                Toast.makeText(NumberPickerActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        //心电图@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        rg_restecg=(RadioGroup)findViewById(R.id.rg_restecg);
        rg_restecg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                str_restecg = radioButton.getText().toString();
                if(str_restecg.equals("轻微")){
                    restecg_int = 0;
                }
                else if(str_restecg.equals("中等")){
                    restecg_int = 1;
                }
                else if(str_restecg.equals("严重")){
                    restecg_int = 2;
                }

//                System.out.println(painType+"");
                Toast.makeText(NumberPickerActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        //exang 运动引发心绞痛
        rg_exang=(RadioGroup)findViewById(R.id.rg_exang);
        rg_exang.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                str_exang = radioButton.getText().toString();
                if(str_exang.equals("是")){
                    exang_int = 1;
                }
                else if(str_exang.equals("否")){
                    exang_int = 1;
                }
//                else if(str_exang.equals("严重")){
//                    exang_int = 2;
//                }

//                System.out.println(painType+"");
                Toast.makeText(NumberPickerActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });

        rg_slop=(RadioGroup)findViewById(R.id.rg_slop);
        rg_slop.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton=(RadioButton)group.findViewById(checkedId);
                str_slop = radioButton.getText().toString();
                System.out.println(str_slop);
                if(str_slop.equals("下降(down)")){
                    slop_int = 0;
                }
                else if(str_slop.equals("平缓(flat)")){
                    slop_int = 1;
                }
                else {
                    slop_int = 2;
                }
//                System.out.println(painType+"");
                Toast.makeText(NumberPickerActivity.this,radioButton.getText(),Toast.LENGTH_SHORT).show();
            }
        });
        // oldpeak 运动相对于休息的ST depression
        et_oldpeak = (EditText) findViewById(R.id.et_oldpeak);
        et_oldpeak.setText(oldpeak +"");
        et_oldpeak.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // 设置初始值
                numberPicker_1.setValue(oldpeak1);
                numberPicker_2.setValue(oldpeak2);

                // 强制隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // 填充布局并设置弹出窗体的宽,高
                popupWindow_oldpeak = new PopupWindow(oldpeak_view,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // 设置弹出窗体可点击
                popupWindow_oldpeak.setFocusable(true);
                // 设置弹出窗体动画效果
                popupWindow_oldpeak.setAnimationStyle(R.style.AnimBottom);
                // 触屏位置如果在选择框外面则销毁弹出框
                popupWindow_oldpeak.setOutsideTouchable(true);
                // 设置弹出窗体的背景
                popupWindow_oldpeak.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                popupWindow_oldpeak.showAtLocation(oldpeak_view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                // 设置背景透明度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.5f;
                getWindow().setAttributes(lp);

                // 添加窗口关闭事件
                popupWindow_oldpeak.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }

                });
            }
        });

        //
        submit_oldpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldpeak1 = numberPicker_1.getValue();
                oldpeak2 = numberPicker_2.getValue();
                oldpeak = oldpeak1+oldpeak2*0.1;
                System.out.println(oldpeak+"oldpeak");
                et_oldpeak.setText(oldpeak + "");
                popupWindow_oldpeak.dismiss();
            }
        });
        //thalach 最大心跳数@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        et_thalach = (EditText) findViewById(R.id.et_thalach);
        et_thalach.setText(thalach + "跳/分钟");
        et_thalach.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // 设置初始值
                numberPicker_thalach.setValue(thalach);

                // 强制隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // 填充布局并设置弹出窗体的宽,高
                popupWindow_thalach = new PopupWindow(thalach_view,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // 设置弹出窗体可点击
                popupWindow_thalach.setFocusable(true);
                // 设置弹出窗体动画效果
                popupWindow_thalach.setAnimationStyle(R.style.AnimBottom);
                // 触屏位置如果在选择框外面则销毁弹出框
                popupWindow_thalach.setOutsideTouchable(true);
                // 设置弹出窗体的背景
                popupWindow_thalach.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                popupWindow_thalach.showAtLocation(thalach_view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                // 设置背景透明度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.5f;
                getWindow().setAttributes(lp);

                // 添加窗口关闭事件
                popupWindow_thalach.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }

                });
            }
        });

        //
        submit_thalach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thalach = numberPicker_thalach.getValue();
                System.out.println(thalach+"thalach");
                et_thalach.setText(thalach + "跳/分钟");
                popupWindow_thalach.dismiss();
            }
        });
        //fbs 空腹血糖 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        et_fbs = (EditText) findViewById(R.id.et_fbs);
        et_fbs.setText(fbs + "mg/dl");
        et_fbs.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // 设置初始值
                numberPicker_fbs.setValue(fbs);

                // 强制隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // 填充布局并设置弹出窗体的宽,高
                popupWindow_fbs = new PopupWindow(fbs_view,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // 设置弹出窗体可点击
                popupWindow_fbs.setFocusable(true);
                // 设置弹出窗体动画效果
                popupWindow_fbs.setAnimationStyle(R.style.AnimBottom);
                // 触屏位置如果在选择框外面则销毁弹出框
                popupWindow_fbs.setOutsideTouchable(true);
                // 设置弹出窗体的背景
                popupWindow_fbs.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                popupWindow_fbs.showAtLocation(fbs_view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                // 设置背景透明度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.5f;
                getWindow().setAttributes(lp);

                // 添加窗口关闭事件
                popupWindow_fbs.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }

                });
            }
        });

        //
        submit_fbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fbs = numberPicker_fbs.getValue();
                System.out.println(fbs+"空腹血糖");
                et_fbs.setText(fbs + "mg/dl");
                popupWindow_fbs.dismiss();
            }
        });
        //血清胆固醇@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        et_chol = (EditText) findViewById(R.id.et_chol);
        et_chol.setText(chol + "mg/dl");
        et_chol.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // 设置初始值
                numberPicker_chol.setValue(chol);

                // 强制隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // 填充布局并设置弹出窗体的宽,高
                popupWindow_chol = new PopupWindow(chol_view,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // 设置弹出窗体可点击
                popupWindow_chol.setFocusable(true);
                // 设置弹出窗体动画效果
                popupWindow_chol.setAnimationStyle(R.style.AnimBottom);
                // 触屏位置如果在选择框外面则销毁弹出框
                popupWindow_chol.setOutsideTouchable(true);
                // 设置弹出窗体的背景
                popupWindow_chol.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                popupWindow_chol.showAtLocation(chol_view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                // 设置背景透明度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.5f;
                getWindow().setAttributes(lp);

                // 添加窗口关闭事件
                popupWindow_chol.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }

                });
            }
        });

        //
        submit_chol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chol = numberPicker_chol.getValue();
                System.out.println(chol+"胆固醇");
                et_chol.setText(chol + "mg/dl");
                popupWindow_chol.dismiss();
            }
        });
        // ca 血管数目
        et_ca = (EditText) findViewById(R.id.et_ca);
        et_ca.setText(ca + "条");
        et_ca.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // 设置初始值
                numberPicker_ca.setValue(ca);

                // 强制隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // 填充布局并设置弹出窗体的宽,高
                popupWindow_ca = new PopupWindow(ca_view,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // 设置弹出窗体可点击
                popupWindow_ca.setFocusable(true);
                // 设置弹出窗体动画效果
                popupWindow_ca.setAnimationStyle(R.style.AnimBottom);
                // 触屏位置如果在选择框外面则销毁弹出框
                popupWindow_ca.setOutsideTouchable(true);
                // 设置弹出窗体的背景
                popupWindow_ca.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                popupWindow_ca.showAtLocation(ca_view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                // 设置背景透明度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.5f;
                getWindow().setAttributes(lp);

                // 添加窗口关闭事件
                popupWindow_ca.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }

                });
            }
        });

        //
        submit_ca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ca = numberPicker_ca.getValue();
                et_ca.setText(ca + "条");
                popupWindow_ca.dismiss();
            }
        });
        // 静息血压 @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        etBloodPressure = (EditText) findViewById(R.id.et_blood_pressure);
        etBloodPressure.setText(bloodPressure + "mmHg");
        etBloodPressure.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                // 设置初始值
                numberPicker_blood_pressure.setValue(bloodPressure);

                // 强制隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // 填充布局并设置弹出窗体的宽,高
                popupWindow_blood_pressure = new PopupWindow(blood_pressure_view,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // 设置弹出窗体可点击
                popupWindow_blood_pressure.setFocusable(true);
                // 设置弹出窗体动画效果
                popupWindow_blood_pressure.setAnimationStyle(R.style.AnimBottom);
                // 触屏位置如果在选择框外面则销毁弹出框
                popupWindow_blood_pressure.setOutsideTouchable(true);
                // 设置弹出窗体的背景
                popupWindow_blood_pressure.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                popupWindow_blood_pressure.showAtLocation(blood_pressure_view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                // 设置背景透明度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.5f;
                getWindow().setAttributes(lp);

                // 添加窗口关闭事件
                popupWindow_blood_pressure.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }

                });
            }
        });

        //
        submit_bloodPressure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bloodPressure = numberPicker_blood_pressure.getValue();
                etBloodPressure.setText(bloodPressure + "mmHg");
                popupWindow_blood_pressure.dismiss();
            }
        });
        // 年龄@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

        edWorkingAge = (EditText) findViewById(R.id.edWorkingAge);
        edWorkingAge.setText(workingAge + "岁");



        // 选择服务年限
        edWorkingAge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // 设置初始值
                numberPicker.setValue(workingAge);

                // 强制隐藏键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);

                // 填充布局并设置弹出窗体的宽,高
                popupWindow = new PopupWindow(workingAge_view,
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                // 设置弹出窗体可点击
                popupWindow.setFocusable(true);
                // 设置弹出窗体动画效果
                popupWindow.setAnimationStyle(R.style.AnimBottom);
                // 触屏位置如果在选择框外面则销毁弹出框
                popupWindow.setOutsideTouchable(true);
                // 设置弹出窗体的背景
                popupWindow.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                popupWindow.showAtLocation(workingAge_view,
                        Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0);

                // 设置背景透明度
                WindowManager.LayoutParams lp = getWindow().getAttributes();
                lp.alpha = 0.5f;
                getWindow().setAttributes(lp);

                // 添加窗口关闭事件
                popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1f;
                        getWindow().setAttributes(lp);
                    }

                });
            }

        });

        // 确定服务年限
        submit_workingAge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                workingAge = numberPicker.getValue();
                edWorkingAge.setText(workingAge + "年");
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 初始化滚动框布局
     */
    private void initNumberPicker() {
        // 年龄
        workingAge_view = LayoutInflater.from(NumberPickerActivity.this).inflate(R.layout.popupwindow, null);
        submit_workingAge = (Button) workingAge_view.findViewById(R.id.submit_workingAge);
        numberPicker = (NumberPicker) workingAge_view.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setFocusable(false);
        numberPicker.setFocusableInTouchMode(false);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker);
        // 血压
        blood_pressure_view = LayoutInflater.from(NumberPickerActivity.this).inflate(R.layout.popwindow_blood_pressure, null);
        submit_bloodPressure = (Button) blood_pressure_view.findViewById(R.id.submit_bloodPressure);
        numberPicker_blood_pressure = (NumberPicker) blood_pressure_view.findViewById(R.id.numberPicker_blood_pressure);
        numberPicker_blood_pressure.setMaxValue(200);
        numberPicker_blood_pressure.setMinValue(0);
        numberPicker_blood_pressure.setFocusable(false);
        numberPicker_blood_pressure.setFocusableInTouchMode(false);
        numberPicker_blood_pressure.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker_blood_pressure);
        // chol血清胆固醇
        chol_view = LayoutInflater.from(NumberPickerActivity.this).inflate(R.layout.popwindow_chol, null);
        submit_chol = (Button) chol_view.findViewById(R.id.submit_chol);
        numberPicker_chol = (NumberPicker) chol_view.findViewById(R.id.numberPicker_chol);
        numberPicker_chol.setMaxValue(200);
        numberPicker_chol.setMinValue(0);
        numberPicker_chol.setFocusable(false);
        numberPicker_chol.setFocusableInTouchMode(false);
        numberPicker_chol.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker_chol);
        // fbs 空腹血糖
        fbs_view = LayoutInflater.from(NumberPickerActivity.this).inflate(R.layout.popwindow_fbs, null);
        submit_fbs = (Button) fbs_view.findViewById(R.id.submit_fbs);
        numberPicker_fbs = (NumberPicker) fbs_view.findViewById(R.id.numberPicker_fbs);
        numberPicker_fbs.setMaxValue(200);
        numberPicker_fbs.setMinValue(0);
        numberPicker_fbs.setFocusable(false);
        numberPicker_fbs.setFocusableInTouchMode(false);
        numberPicker_fbs.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker_fbs);
        // thalach 最大心跳数
        thalach_view = LayoutInflater.from(NumberPickerActivity.this).inflate(R.layout.popwindow_thalach, null);
        submit_thalach = (Button) thalach_view.findViewById(R.id.submit_thalach);
        numberPicker_thalach = (NumberPicker) thalach_view.findViewById(R.id.numberPicker_thalach);
        numberPicker_thalach.setMaxValue(200);
        numberPicker_thalach.setMinValue(0);
        numberPicker_thalach.setFocusable(false);
        numberPicker_thalach.setFocusableInTouchMode(false);
        numberPicker_thalach.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker_thalach);
        // ca 血管数目
        ca_view = LayoutInflater.from(NumberPickerActivity.this).inflate(R.layout.popwindow_ca, null);
        submit_ca = (Button) ca_view.findViewById(R.id.submit_ca);
        numberPicker_ca = (NumberPicker) ca_view.findViewById(R.id.numberPicker_ca);
        numberPicker_ca.setMaxValue(3);
        numberPicker_ca.setMinValue(0);
        numberPicker_ca.setFocusable(false);
        numberPicker_ca.setFocusableInTouchMode(false);
        numberPicker_ca.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker_ca);
        //oldpeak
        oldpeak_view = LayoutInflater.from(NumberPickerActivity.this).inflate(R.layout.popwindow_oldpeak, null);
        submit_oldpeak = (Button) oldpeak_view.findViewById(R.id.submit_oldpeak);

        numberPicker_1 = (NumberPicker) oldpeak_view.findViewById(R.id.numberPicker_1);
        numberPicker_1.setMaxValue(20);
        numberPicker_1.setMinValue(0);
        numberPicker_1.setFocusable(false);
        numberPicker_1.setFocusableInTouchMode(false);
        numberPicker_1.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker_1);

        numberPicker_2 = (NumberPicker) oldpeak_view.findViewById(R.id.numberPicker_2);
        numberPicker_2.setMaxValue(20);
        numberPicker_2.setMinValue(0);
        numberPicker_2.setFocusable(false);
        numberPicker_2.setFocusableInTouchMode(false);
        numberPicker_2.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker_2);
    }

    /**
     * 自定义滚动框分隔线颜色
     */
    private void setNumberPickerDividerColor(NumberPicker number) {
        Field[] pickerFields = NumberPicker.class.getDeclaredFields();
        for (Field pf : pickerFields) {
            if (pf.getName().equals("mSelectionDivider")) {
                pf.setAccessible(true);
                try {
                    //设置分割线的颜色值
                    pf.set(number, new ColorDrawable(ContextCompat.getColor(this, R.color.numberpicker_line)));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }
    }



}
