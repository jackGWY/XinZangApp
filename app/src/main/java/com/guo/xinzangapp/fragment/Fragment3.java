package com.guo.xinzangapp.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.guo.xinzangapp.MyActivity;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.consult.FindActivity;
import com.guo.xinzangapp.diary.diaryActivity;
import com.guo.xinzangapp.sports.FeelingListActivity;

public class Fragment3 extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment3, null);
        initView();

        LinearLayout linear_modify = (LinearLayout)view.findViewById(R.id.linear_modify);
        linear_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), MyActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linear_binglidan = (LinearLayout)view.findViewById(R.id.linear_binglidan);
        linear_binglidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), diaryActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linear_diary = (LinearLayout)view.findViewById(R.id.linear_diary);
        linear_diary.setOnClickListener(new View.OnClickListener() {
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


        LinearLayout linear_mypatients = (LinearLayout)view.findViewById(R.id.linear_mypatients);
        linear_mypatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(getActivity(), FindActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout linear_gengxin = (LinearLayout)view.findViewById(R.id.linear_gengxin);
        linear_gengxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://diml.ecnu.edu.cn/download/heartapp.html");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        });

        return view;
    }

    private void initView() {

    }


}
