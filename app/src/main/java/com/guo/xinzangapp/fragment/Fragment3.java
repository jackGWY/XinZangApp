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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.guo.xinzangapp.MyActivity;
import com.guo.xinzangapp.R;
import com.guo.xinzangapp.consult.FindActivity;
import com.guo.xinzangapp.diary.diaryActivity;
import com.guo.xinzangapp.diary.diaryListActivity;
import com.guo.xinzangapp.doctors.DocSwitchActivity;
import com.guo.xinzangapp.doctors.DoctorsPatientListActivity;
import com.guo.xinzangapp.sports.FeelingListActivity;

import org.w3c.dom.Text;

public class Fragment3 extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment3, null);
        initView();

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        final String userName = pref.getString("userName","");
        final String userType = pref.getString("userType","");
        TextView tv_mypatiens = (TextView) view.findViewById(R.id.tv_mypatients);

        if(userType.equals("patient")){
            tv_mypatiens.setText("我的医生");
        } else {
            tv_mypatiens.setText("我的病人");
        }

        TextView tv_my_name = (TextView) view.findViewById(R.id.tv_my_title);
        tv_my_name.setText(userName);
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
        TextView tv_doc_binglidan = (TextView) view.findViewById(R.id.tv_doc_binglidan);
        if(userType.equals("doctor")){
            tv_doc_binglidan.setText("病人病历单");
        }
        linear_binglidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent();
//                //SoilsenerActivity.class为想要跳转的Activity
//                intent.setClass(getActivity(), diaryListActivity.class);
//                startActivity(intent);

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

        LinearLayout linear_diary = (LinearLayout)view.findViewById(R.id.linear_diary);
        TextView tv_doc_feeling = (TextView)view.findViewById(R.id.tv_doc_feeling);
        if(userType.equals("doctor")){
            tv_doc_feeling.setText("病人运动日记");
        }
        linear_diary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Bundle bundle = new Bundle();
//
//                bundle.putString("fromPatientListAdapter","nothing");
//                bundle.putString("patientName","nothing");
//                Intent intent = new Intent();
//
//                //SoilsenerActivity.class为想要跳转的Activity
//                intent.setClass(getActivity(), FeelingListActivity.class);
//                intent.putExtra("Message",bundle);
//                startActivity(intent);

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


        LinearLayout linear_mypatients = (LinearLayout)view.findViewById(R.id.linear_mypatients);
        linear_mypatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(userType.equals("patient")){
                    Intent intent = new Intent();
                    //SoilsenerActivity.class为想要跳转的Activity
                    intent.setClass(getActivity(), FindActivity.class);
                    startActivity(intent);
                }
                else {
                    Intent intent = new Intent();
                    //SoilsenerActivity.class为想要跳转的Activity
                    intent.setClass(getActivity(), DocSwitchActivity.class);
                    startActivity(intent);
                }

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
