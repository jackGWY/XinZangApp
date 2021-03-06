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
import com.guo.xinzangapp.consult.consultActivity;


import butterknife.BindView;
import butterknife.OnClick;

public class DocSwitchActivity extends AppCompatActivity {


    private Button btnPatientFeeling,btnMyPatients,btn_discuss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_switch);

        btnPatientFeeling = (Button)findViewById(R.id.btn_patient_feeling);
        btnMyPatients = (Button) findViewById(R.id.btn_my_patient);

        btnPatientFeeling.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //startActivity(new Intent(DocSwitchActivity.this,DoctorsPatientListActivity.class));
                // DoctorsPatientListActivity
                Bundle bundle = new Bundle();

                bundle.putString("fromWhere","feeling");
                Intent intent = new Intent();

                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(DocSwitchActivity.this, DoctorsPatientListActivity.class);
                intent.putExtra("Message",bundle);
                startActivity(intent);
            }
        });
        btnMyPatients.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                startActivity(new Intent(DocSwitchActivity.this,FindActivity.class));
            }
        });

        btn_discuss = (Button)findViewById(R.id.btn_doc_discuss);
        btn_discuss.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                //startActivity(new Intent(DocSwitchActivity.this,consultActivity.class));
                Bundle bundle = new Bundle();

                bundle.putString("fromWhere","data");
                Intent intent = new Intent();

                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(DocSwitchActivity.this, DoctorsPatientListActivity.class);
                intent.putExtra("Message",bundle);
                startActivity(intent);
            }
        });

        Button btn_doc_binglidan = (Button) findViewById(R.id.btn_doc_binglidan);
        btn_doc_binglidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();

                bundle.putString("fromWhere","binglidan");
                Intent intent = new Intent();

                //SoilsenerActivity.class为想要跳转的Activity
                intent.setClass(DocSwitchActivity.this, DoctorsPatientListActivity.class);
                intent.putExtra("Message",bundle);
                startActivity(intent);
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
