package com.guo.xinzangapp.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guo.xinzangapp.FoodActivity;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.consult.ChatActivity;
import com.guo.xinzangapp.consult.FindActivity;
import com.guo.xinzangapp.consult.consultActivity;
import com.guo.xinzangapp.diary.diaryActivity;
import com.guo.xinzangapp.heartrate.HeartRateActivity;
import com.guo.xinzangapp.index.NumberPickerActivity;
import com.guo.xinzangapp.medicine.drugTypeListActivity;
import com.guo.xinzangapp.medicineArticle.newListActivity;
import com.guo.xinzangapp.sports.FeelingListActivity;

public class Fragment1 extends Fragment {
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment1, null);
        initView();

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
        image_binglidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), diaryActivity.class);
                startActivity(intent);
            }
        });

        // 新闻资料 ***************************
        ImageView image_news=(ImageView) view.findViewById(R.id.image_news);
        image_news.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), newListActivity.class);
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

        // feeling 日记 ***************************
        ImageView image_feeling=(ImageView) view.findViewById(R.id.image_feeling);
        image_feeling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("fromPatientListAdapter","nothing");
                bundle.putString("patientName","nothing");
                Intent intent = new Intent();

                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), FeelingListActivity.class);
                intent.putExtra("Message",bundle);
                startActivity(intent);
            }
        });

        // 购买药物 ***************************
        ImageView image_buy_drug=(ImageView) view.findViewById(R.id.image_buy_drug);
        image_buy_drug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Uri uri = Uri.parse("https://pages.tmall.com/wow/yao/act/ziyinghome?from=zebra:offline");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);

//                Intent intent = new Intent();
//                //SoilsenerActivity.class为想要跳转的Activity
//                intent.setClass(getActivity(), newListActivity.class);
//                startActivity(intent);
            }
        });

        return view;
    }

    private void initView() {

    }


}
