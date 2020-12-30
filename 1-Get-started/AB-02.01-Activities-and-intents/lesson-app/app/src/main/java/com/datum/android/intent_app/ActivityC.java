package com.datum.android.intent_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ActivityC extends AppCompatActivity {

    TextView mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c);

        mMessage = findViewById(R.id.txt_message);


        Intent intent = getIntent();
        String data = intent.getStringExtra(GlobalConstraints.INTENT_KEY);
        mMessage.setText(data);


        /**
         * Below is another way used to fetch the same data using Bundle instead of Intent
         *
         */

//        Bundle bundle = getIntent().getExtras();
//        if(bundle != null) {
//            String data = bundle.getString(GlobalConstraints.INTENT_KEY);
//            mMessage.setText(data);
//        }

    }

    public void navigateBack(View view) {

        Intent intent = new Intent(this, ActivityA.class);
        startActivity(intent);
    }
}