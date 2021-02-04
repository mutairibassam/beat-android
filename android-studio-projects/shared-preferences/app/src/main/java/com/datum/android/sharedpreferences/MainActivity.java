package com.datum.android.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // read
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        SharedPreferences spx = androidx.preference.PreferenceManager.getDefaultSharedPreferences(this);

        // mode private == no body can access from outside the application
        SharedPreferences sp_specific_name = getSharedPreferences("names", MODE_PRIVATE);

        // edit
        SharedPreferences.Editor editor = sp.edit();

        editor.putString("name", "Bassam");
        editor.apply();

        sp.getString("name", "No name");


    }
}