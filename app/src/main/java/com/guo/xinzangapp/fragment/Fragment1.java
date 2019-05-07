package com.guo.xinzangapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guo.xinzangapp.R;
import com.guo.xinzangapp.heartrate.HeartRateActivity;

public class Fragment1 extends Fragment {
    private View view;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment1, null);
        initView();

//        ImageView image_heart_rate=(ImageView) view.findViewById(R.id.image_heart_rate);
//        image_heart_rate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent();
//                //SoilsenerActivity.class为想要跳转的Activity
//                intent.setClass(getActivity(), HeartRateActivity.class);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    private void initView() {

    }


}
