package com.guo.xinzangapp.index;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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

import java.lang.reflect.Field;

public class NumberPickerActivity extends AppCompatActivity {

    //年龄
    private EditText edWorkingAge;
    private Button submit_workingAge,submitWorkingAge;
    private PopupWindow popupWindow;
    private NumberPicker numberPicker;
    private View workingAge_view;
    private int workingAge = 0;

    //男女
    private RadioGroup mRg1;
    private String sex = "男";

    //疼痛类型
    private RadioGroup rg_chest_pain_type;
    private String chest_pain_type;
    private int painType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_picker);

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

        // 年龄@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        submitWorkingAge = (Button) findViewById(R.id.submitWorkingAge);
        edWorkingAge = (EditText) findViewById(R.id.edWorkingAge);
        edWorkingAge.setText(workingAge + "年");

        initNumberPicker();

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
        workingAge_view = LayoutInflater.from(NumberPickerActivity.this).inflate(R.layout.popupwindow, null);
        submit_workingAge = (Button) workingAge_view.findViewById(R.id.submit_workingAge);
        numberPicker = (NumberPicker) workingAge_view.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(100);
        numberPicker.setMinValue(0);
        numberPicker.setFocusable(false);
        numberPicker.setFocusableInTouchMode(false);
        numberPicker.setDescendantFocusability(NumberPicker.FOCUS_BLOCK_DESCENDANTS);
        setNumberPickerDividerColor(numberPicker);
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
