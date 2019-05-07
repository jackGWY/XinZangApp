package com.guo.xinzangapp.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.guo.xinzangapp.MyActivity;
import com.guo.xinzangapp.R;

public class Fragment2 extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment2, null);
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

        return view;
    }

    private void initView() {

    }


}
