package com.guo.xinzangapp.fragment;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.guo.xinzangapp.BarChart.BarChartActivity;
import com.guo.xinzangapp.FoodActivity;
import com.guo.xinzangapp.MyActivity;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.consult.ChatActivity;
import com.guo.xinzangapp.consult.FindActivity;
import com.guo.xinzangapp.consult.consultActivity;
import com.guo.xinzangapp.diary.diaryActivity;
import com.guo.xinzangapp.diary.diaryListActivity;
import com.guo.xinzangapp.doctors.DocSwitchActivity;
import com.guo.xinzangapp.doctors.DoctorsPatientListActivity;
import com.guo.xinzangapp.heartrate.HeartRateActivity;
import com.guo.xinzangapp.index.NumberPickerActivity;
import com.guo.xinzangapp.medicine.drugTypeListActivity;
import com.guo.xinzangapp.medicineArticle.heartNewsListActivity;
import com.guo.xinzangapp.medicineArticle.newListActivity;
import com.guo.xinzangapp.sports.FeelingListActivity;
import com.guo.xinzangapp.step.StepMainActivity;

public class Fragment1 extends Fragment {
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment1, null);
        initView();

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String userName = pref.getString("userName","");
        final String userType = pref.getString("userType","");

        ImageView image_heart_rate=(ImageView) view.findViewById(R.id.image_heart_rate);
        image_heart_rate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), HeartRateActivity.class);
                startActivity(intent);
            }
        });

        // 聊天 ***************************
        ImageView image_chart=(ImageView) view.findViewById(R.id.image_chat);
        image_chart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), FindActivity.class);
                startActivity(intent);
            }
        });

        // 发帖讨论 ***************************
        ImageView image_discuss=(ImageView) view.findViewById(R.id.image_discuss2);
        image_discuss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), consultActivity.class);
                startActivity(intent);
            }
        });

        // 药物信息 ***************************
        ImageView image_drug_info=(ImageView) view.findViewById(R.id.image_drug_info);
        image_drug_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), drugTypeListActivity.class);
                startActivity(intent);
            }
        });

        // 病历单 ***************************
        ImageView image_binglidan=(ImageView) view.findViewById(R.id.image_binglidan);
        TextView tv_binglidan = (TextView) view.findViewById(R.id.tv_binglidan);
        if(userType.equals("doctor")){
            tv_binglidan.setText("病人病历单");
        }
        image_binglidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userType.equals("patient")){
//                    Intent intent = new Intent();
//                    //SoilsenerActivity.class为想要跳转的Activity
//                    intent.setClass(getActivity(), diaryListActivity.class);
//                    startActivity(intent);
                    Bundle bundle = new Bundle();
                    bundle.putString("patientName","nothing");

                    Intent intent = new Intent(getActivity(),diaryListActivity.class);
//                intent.putExtras(bundle);
                    intent.putExtra("Message",bundle);
                    startActivity(intent);
                }
                else {
                    Bundle bundle = new Bundle();

                    bundle.putString("fromWhere","binglidan");
                    Intent intent = new Intent();

                    //SoilsenerActivity.class为想要跳转的Activity
                    intent.setClass(getActivity(), DoctorsPatientListActivity.class);
                    intent.putExtra("Message",bundle);
                    startActivity(intent);
                }
            }
        });

        // 新闻资料 ***************************
        ImageView image_news=(ImageView) view.findViewById(R.id.image_news);
        image_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), heartNewsListActivity.class);
                startActivity(intent);
            }
        });

        // 食物推荐 ***************************
        ImageView image_food=(ImageView) view.findViewById(R.id.image_food);
        image_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), FoodActivity.class);
                startActivity(intent);
            }
        });

        // 预测 ***************************
        ImageView image_predict=(ImageView) view.findViewById(R.id.predict);
        image_predict.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), NumberPickerActivity.class);
                startActivity(intent);
            }
        });

        // data我的数据 ***************************
        ImageView image_data=(ImageView) view.findViewById(R.id.image_data);
        TextView tv_patient_data = view.findViewById(R.id.tv_patient_data);
        if(!userType.equals("patient")) {
            tv_patient_data.setText("病人数据");
        }
        image_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userType.equals("patient")){
//                    Intent intent = new Intent();
//                    //SoilsenerActivity.class为想要跳转的Activity
//                    intent.setClass(getActivity(), BarChartActivity.class);
//                    startActivity(intent);

                    Bundle bundle = new Bundle();
                    bundle.putString("patientName","nothing");

                    Intent intent = new Intent(getActivity(),BarChartActivity.class);
//                intent.putExtras(bundle);
                    intent.putExtra("Message",bundle);
                    startActivity(intent);
                }
                else {
//                    Intent intent = new Intent();
//                    //SoilsenerActivity.class为想要跳转的Activity
//                    intent.setClass(getActivity(), DocSwitchActivity.class);
//                    startActivity(intent);
                    Bundle bundle = new Bundle();

                    bundle.putString("fromWhere","data");
                    Intent intent = new Intent();

                    //SoilsenerActivity.class为想要跳转的Activity
                    intent.setClass(getActivity(), DoctorsPatientListActivity.class);
                    intent.putExtra("Message",bundle);
                    startActivity(intent);
                }


            }
        });

        // 修改个人信息***************************
        ImageView image_modify=(ImageView) view.findViewById(R.id.image_modify);
        image_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), MyActivity.class);
                startActivity(intent);
            }
        });

        // feeling 日记 ***************************
        ImageView image_feeling=(ImageView) view.findViewById(R.id.image_feeling);
        TextView tv_patient_diary = (TextView) view.findViewById(R.id.tv_patient_diary);
        if(userType.equals("doctor")){
            tv_patient_diary.setText("病人日记");
        }
        image_feeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userType.equals("patient")){
                    Bundle bundle = new Bundle();

                    bundle.putString("fromPatientListAdapter","nothing");
                    bundle.putString("patientName","nothing");
                    Intent intent = new Intent();

                    //SoilsenerActivity.class为想要跳转的Activity
                    intent.setClass(getActivity(), FeelingListActivity.class);
                    intent.putExtra("Message",bundle);
                    startActivity(intent);
                }
                else {
                    // DoctorsPatientListActivity
                    Bundle bundle = new Bundle();

                    bundle.putString("fromWhere","feeling");
                    Intent intent = new Intent();

                    //SoilsenerActivity.class为想要跳转的Activity
                    intent.setClass(getActivity(), DoctorsPatientListActivity.class);
                    intent.putExtra("Message",bundle);
                    startActivity(intent);
                }

            }
        });

//        // 购买药物 ***************************
//        ImageView image_buy_drug=(ImageView) view.findViewById(R.id.image_buy_drug);
//        image_buy_drug.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Uri uri = Uri.parse("https://pages.tmall.com/wow/yao/act/ziyinghome?from=zebra:offline");
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(uri);
//                startActivity(intent);
//            }
//        });

//        // 查找医院***************************
//        ImageView image_hospital=(ImageView) view.findViewById(R.id.image_hospital2);
//        image_hospital.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("https://map.baidu.com/mobile/webapp/search/search/qt=con&wd=%E5%8C%BB%E9%99%A2&c=289&newmap=1&from=alamap&tpl=mapdots");
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(uri);
//                startActivity(intent);
//            }
//        });

//        // 官网更新app***************************
//        ImageView image_gengxin=(ImageView) view.findViewById(R.id.image_gengxin);
//        image_gengxin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("http://diml.ecnu.edu.cn/download/heartapp.html");
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(uri);
//                startActivity(intent);
////                Intent intent = new Intent();
////                //SoilsenerActivity.class为想要跳转的Activity
////                intent.setClass(getActivity(), StepMainActivity.class);
////                startActivity(intent);
//
//            }
//        });
//
//        // 挂号***************************
//        ImageView image_guahao=(ImageView) view.findViewById(R.id.image_guahao);
//        image_guahao.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Uri uri = Uri.parse("https://shanghai.guahao.com/");
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.VIEW");
//                intent.setData(uri);
//                startActivity(intent);
//            }
//        });

        // 急救电话
        ImageView image_phone=(ImageView) view.findViewById(R.id.image_phone);
        image_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                call("120");
            }
        });

        return view;
    }

    private void initView() {

    }

    private void call(String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:"+phone));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }


}
