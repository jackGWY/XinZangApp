package com.guo.xinzangapp.fragment;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.guo.xinzangapp.R;

public class Fragment4 extends Fragment {
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = (View) inflater.inflate(R.layout.fragment4, null);
        initView();


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
            }
        });

        // 查找医院***************************
        ImageView image_hospital=(ImageView) view.findViewById(R.id.image_hospital2);
        image_hospital.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://map.baidu.com/mobile/webapp/search/search/qt=con&wd=%E5%8C%BB%E9%99%A2&c=289&newmap=1&from=alamap&tpl=mapdots");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
            }
        });

       // 官网更新app***************************
        ImageView image_gengxin=(ImageView) view.findViewById(R.id.image_gengxin);
        image_gengxin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("http://diml.ecnu.edu.cn/download/heartapp.html");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
//                Intent intent = new Intent();
//                //SoilsenerActivity.class为想要跳转的Activity
//                intent.setClass(getActivity(), StepMainActivity.class);
//                startActivity(intent);

            }
        });

        // 挂号***************************
        ImageView image_guahao=(ImageView) view.findViewById(R.id.image_guahao);
        image_guahao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://shanghai.guahao.com/");
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
