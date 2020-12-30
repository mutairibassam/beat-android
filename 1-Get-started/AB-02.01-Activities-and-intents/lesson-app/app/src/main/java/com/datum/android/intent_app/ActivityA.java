package com.datum.android.intent_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class ActivityA extends AppCompatActivity {

    EditText mMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_a);

        mMessage = findViewById(R.id.et_message);
    }

    public void navigateNext(View view) {

        Intent intent = new Intent(this, ActivityB.class);
        startActivity(intent);

    }

    public void passData(View view) {
        String message = mMessage.getText().toString();

        Intent intent = new Intent(this, ActivityC.class);
        intent.putExtra(GlobalConstraints.INTENT_KEY, message);
        startActivity(intent);
    }
}