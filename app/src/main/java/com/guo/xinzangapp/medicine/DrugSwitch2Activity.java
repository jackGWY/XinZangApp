package com.guo.xinzangapp.medicine;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.guo.xinzangapp.R;
import com.guo.xinzangapp.diary.diaryActivity;
import com.guo.xinzangapp.medicineArticle.newListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DrugSwitch2Activity extends AppCompatActivity {

    @BindView(R.id.btn_drug_info)
    Button btn_drug_info;
    @BindView(R.id.btn_buy_drug)
    Button btn_buy_drug;
    @BindView(R.id.btn_records)
    Button btn_records;
    @BindView(R.id.btn_news)
    Button btn_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_switch2);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_drug_info,R.id.btn_news,R.id.btn_records,R.id.btn_buy_drug})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.btn_drug_info:
                startActivity(new Intent(DrugSwitch2Activity.this,drugTypeListActivity.class));
                break;

            case R.id.btn_news:
                startActivity(new Intent(DrugSwitch2Activity.this,newListActivity.class));
                break;

            case R.id.btn_records:
                startActivity(new Intent(DrugSwitch2Activity.this,diaryActivity.class));
                break;
            case R.id.btn_buy_drug:
//                startActivity(new Intent(DrugSwitch2Activity.this,diaryActivity.class));
                Uri uri = Uri.parse("https://pages.tmall.com/wow/yao/act/ziyinghome?from=zebra:offline");
                Intent intent = new Intent();
                intent.setAction("android.intent.action.VIEW");
                intent.setData(uri);
                startActivity(intent);
                break;
        }
    }
}
