package com.guo.xinzangapp.medicine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.guo.xinzangapp.R;
import com.guo.xinzangapp.diary.diaryActivity;
import com.guo.xinzangapp.diary.diaryListActivity;
import com.guo.xinzangapp.medicineArticle.newListActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class drugSwitchActivity extends AppCompatActivity {

    @BindView(R.id.textView_classification)
    TextView classification;
    @BindView(R.id.image_button_classification)
    ImageButton imageBtnClass;
    @BindView(R.id.tv_news)
    TextView tv_news;
    @BindView(R.id.image_button_news)
    ImageButton imageBtnNews;
    @BindView(R.id.tv_diary)
    TextView tv_diary;
    @BindView(R.id.image_button_diary)
    ImageButton imageBtnDiary;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drug_switch);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.textView_classification,R.id.image_button_classification,R.id.tv_news,R.id.image_button_news,R.id.tv_diary,R.id.image_button_diary})
    public void onClick(final View view) {
        switch (view.getId()) {
            case R.id.textView_classification:
                startActivity(new Intent(drugSwitchActivity.this,drugTypeListActivity.class));
                break;
            case R.id.image_button_classification:
                startActivity(new Intent(drugSwitchActivity.this,drugTypeListActivity.class));
                break;
            case R.id.tv_news:
                startActivity(new Intent(drugSwitchActivity.this,newListActivity.class));
                break;
            case R.id.image_button_news:
                startActivity(new Intent(drugSwitchActivity.this,newListActivity.class));
                break;
            case R.id.tv_diary:
                startActivity(new Intent(drugSwitchActivity.this,diaryActivity.class));
                break;
            case R.id.image_button_diary:
                startActivity(new Intent(drugSwitchActivity.this,diaryActivity.class));
                break;
        }
    }
}
