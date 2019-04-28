package com.guo.xinzangapp.doctors;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;


import com.guo.xinzangapp.R;
import com.guo.xinzangapp.consult.FindActivity;
import com.guo.xinzangapp.consult.PatientListActivity;


import butterknife.BindView;
import butterknife.OnClick;

public class DocSwitchActivity extends AppCompatActivity {


    private Button btnPatientFeeling,btnMyPatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_switch);

        btnPatientFeeling = (Button)findViewById(R.id.btn_patient_feeling);
        btnMyPatients = (Button) findViewById(R.id.btn_my_patient);
        btnMyPatients.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(DocSwitchActivity.this,DoctorsPatientListActivity.class));
            }
        });
        btnPatientFeeling.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                startActivity(new Intent(DocSwitchActivity.this,FindActivity.class));
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
