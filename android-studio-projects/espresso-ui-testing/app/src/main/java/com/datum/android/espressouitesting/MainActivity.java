package com.datum.android.espressouitesting;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button mLogin;
    EditText mUsername, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsername = findViewById(R.id.et_username);
        mPassword = findViewById(R.id.et_password);

        mLogin = findViewById(R.id.login_button);
        mLogin.setOnClickListener(view -> {
            Intent intent = new Intent(getApplicationContext(), MessageActivity.class);
            intent.putExtra("username", mUsername.getText().toString());
            intent.putExtra("password", mPassword.getText().toString());
            startActivity(intent);
        });


    }
}