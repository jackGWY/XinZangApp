package com.guo.xinzangapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.guo.xinzangapp.consult.ConsultSwitchActivity;
import com.guo.xinzangapp.consult.consultActivity;
import com.guo.xinzangapp.doctors.DocSwitchActivity;
import com.guo.xinzangapp.hospital.hospitalActivity;
import com.guo.xinzangapp.hospital.hospitalSwitchActivity;
import com.guo.xinzangapp.index.IndexSwitchActivity;
import com.guo.xinzangapp.index.indexActivity;
import com.guo.xinzangapp.medicine.drugSwitchActivity;

import butterknife.BindView;

public class homeActivity extends AppCompatActivity {

    private ImageButton imageMedicine, imageFood,imageIndex,imageDoctor,imageHospital;
    private TextView mtvMedicine, mtvFood,mtvIndex,mtvDoctor,mtvHospital;

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.toolbar,menu);
        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case R.id.addtion:
                Toast.makeText(this,"you click add",Toast.LENGTH_SHORT).show();
                break;
        }
        return true;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");
        final String userType = pref.getString("userType","");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        //setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        mtvMedicine = (TextView) findViewById(R.id.textView_medicine2);
        imageMedicine =(ImageButton)findViewById(R.id.image_button_medicine);

        imageFood = (ImageButton) findViewById(R.id.image_button_food);
        mtvFood = (TextView)findViewById(R.id.textView_food);

        imageIndex = (ImageButton) findViewById(R.id.image_index);
        mtvIndex = (TextView) findViewById(R.id.textView_index);

        imageDoctor = (ImageButton) findViewById(R.id.image_doctor);
        mtvDoctor =  (TextView) findViewById(R.id.textView_doctor);

        imageHospital = (ImageButton) findViewById(R.id.image_hospital);
        mtvHospital = (TextView) findViewById(R.id.textView_hospital);


        mtvMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, drugSwitchActivity.class));
//                Toast.makeText(homeActivity.this,"btn_4我被点击了",Toast.LENGTH_SHORT).show();
            }
        });

        imageMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, drugSwitchActivity.class));
//                Toast.makeText(homeActivity.this,"btn_4我被点击了",Toast.LENGTH_SHORT).show();
            }
        });

        imageFood.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, FoodActivity.class));
            }
        });

        mtvFood.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, FoodActivity.class));
            }
        });

        imageIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, IndexSwitchActivity.class));
            }
        });

        mtvIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, IndexSwitchActivity.class));
            }
        });

        imageDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (userType.equals("doctor")) {
                    startActivity(new Intent(homeActivity.this, DocSwitchActivity.class));
                } else {
                    startActivity(new Intent(homeActivity.this, ConsultSwitchActivity.class));
                }

            }
        });

        //mtvDoctor
        mtvDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, ConsultSwitchActivity.class));
            }
        });

        imageHospital.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, hospitalSwitchActivity.class));
            }
        });
    }

}
