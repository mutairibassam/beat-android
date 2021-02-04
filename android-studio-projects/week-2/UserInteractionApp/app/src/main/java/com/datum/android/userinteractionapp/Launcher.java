package com.datum.android.userinteractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.datum.android.userinteractionapp.databinding.ActivityLauncherBinding;

public class Launcher extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = Launcher.class.getSimpleName();

    private ActivityLauncherBinding binding;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String mUsermail, mPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLauncherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.tvGuest.setOnClickListener(View -> navigate());

        binding.loginButton.setOnClickListener(this);
    }


    private void getData() {
        mUsermail = binding.etEmail.getText().toString();
        mPassword = binding.etPassword.getText().toString();
        if (mUsermail.equals("") || mPassword.equals("")) {
            Toast.makeText(this, "Please enter your credentional", Toast.LENGTH_LONG).show();
        } else {
            storeData();
        }
    }

    private void storeData() {
        sharedPreferences = getSharedPreferences(GlobalConstants.USERS, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        editor.putString(GlobalConstants.USER_EMAIL, mUsermail);
        editor.apply();
        navigate();
    }


    @Override
    public void onClick(View view) {
        getData();
    }

    public void navigate() {
        startActivity(new Intent(Launcher.this, MainActivity.class));
    }
}