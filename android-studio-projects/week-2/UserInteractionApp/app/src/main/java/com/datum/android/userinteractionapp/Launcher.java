package com.datum.android.userinteractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

    }

    public void newActivity(View view) {

        startActivity(new Intent(this, NewActivity.class));
    }

    public void oldActivity(View view) {

        startActivity(new Intent(this, MainActivity.class));
    }
}