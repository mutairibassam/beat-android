package com.datum.android.recyclerviewapp.newlayout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.datum.android.recyclerviewapp.R;

public class newSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_second);

        Intent intent = getIntent();
        String data = intent.getStringExtra("item");
        Log.d("TAG", "onCreate: data == " + data);
    }
}