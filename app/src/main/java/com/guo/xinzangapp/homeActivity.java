package com.guo.xinzangapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;

public class homeActivity extends AppCompatActivity {

   private ImageButton imageMedicine;

    private TextView mtvMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        mtvMedicine = (TextView) findViewById(R.id.textView_medicine2);
        imageMedicine =(ImageButton)findViewById(R.id.image_button_medicine);

        mtvMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, medicineActivity.class));
//                Toast.makeText(homeActivity.this,"btn_4我被点击了",Toast.LENGTH_SHORT).show();
            }
        });

        imageMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(homeActivity.this, medicineActivity.class));
//                Toast.makeText(homeActivity.this,"btn_4我被点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }

}
