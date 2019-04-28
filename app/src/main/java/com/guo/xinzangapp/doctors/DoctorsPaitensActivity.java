package com.guo.xinzangapp.doctors;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.guo.xinzangapp.R;

public class DoctorsPaitensActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctors_paitens);

        SharedPreferences pref;
        SharedPreferences.Editor editor;
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        final String userName = pref.getString("userName","");
        final String userType = pref.getString("userType","");


    }
}
