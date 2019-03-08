package com.guo.xinzangapp.doctors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;


import com.guo.xinzangapp.R;


import butterknife.BindView;
import butterknife.OnClick;

public class DocSwitchActivity extends AppCompatActivity {

    private ImageButton imageButton;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_switch);
        imageButton = (ImageButton)findViewById(R.id.image_patient);
        textView = (TextView)findViewById(R.id.textView_doctor);
        imageButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DocSwitchActivity.this,DocPatientRecordActivity.class));
            }
        });
    }

//    @OnClick({R.id.image_patient, R.id.textView_doctor})
//    public void onClick(final View view) {
//        Intent intent = new Intent();
//        switch (view.getId()) {
//            case R.id.image_patient:
//                intent = new Intent(DocSwitchActivity.this,DocPatientRecordActivity.class);
//                startActivity(intent);
//                break;
//            case R.id.textView_doctor:
//                startActivity(new Intent(DocSwitchActivity.this,DocPatientRecordActivity.class));
//                break;
//        }
//    }
}
