package com.guo.xinzangapp;

import android.content.Intent;
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

import butterknife.BindView;

public class homeActivity extends AppCompatActivity {

    private ImageButton imageMedicine, imageFood;
    private TextView mtvMedicine, mtvFood;

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.toolbar);
        //setSupportActionBar(toolbar);
        setSupportActionBar(toolbar);

        mtvMedicine = (TextView) findViewById(R.id.textView_medicine2);
        imageMedicine =(ImageButton)findViewById(R.id.image_button_medicine);

        imageFood = (ImageButton) findViewById(R.id.image_button_food);
        mtvFood = findViewById(R.id.textView_food);


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
    }

}
