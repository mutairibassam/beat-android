package com.datum.android.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // read
//        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences spx = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);
        // edit
        SharedPreferences.Editor editor = spx.edit();
        editor.putString("name", "bassam");
        editor.apply();

        // mode private == no body can access from outside the application
//        SharedPreferences sp_specific_name = getSharedPreferences("myown_sp", MODE_PRIVATE);


        // how to get the data
        spx.getString("name", "No name");





    }
}