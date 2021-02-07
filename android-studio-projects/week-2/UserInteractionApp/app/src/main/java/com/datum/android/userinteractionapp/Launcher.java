package com.datum.android.userinteractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.datum.android.userinteractionapp.databinding.ActivityLauncherBinding;

public class Launcher extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {

    private static final String TAG = Launcher.class.getSimpleName();

    private ActivityLauncherBinding binding;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    String mUsermail, mPassword;

    boolean isRememberClicked;
    boolean isGuest = false;

    CheckBox checkBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLauncherBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        getInit();

        binding.tvGuest.setOnClickListener(View -> {
            isGuest = true;
            navigate();
        });

        binding.loginButton.setOnClickListener(View -> {
            isGuest = false;
            getData();
        });
    }

    private void getInit() {

        checkBox = findViewById(R.id.checkBox);
        checkBox.setOnCheckedChangeListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();

        if(isRememberClicked) {
            sharedPreferences = getSharedPreferences("remember_me", MODE_PRIVATE);
            editor = sharedPreferences.edit();

            String username =  binding.etEmail.getText().toString();
            editor.putString("USER_NAME", username);
            editor.putBoolean(GlobalConstants.REMEMBER_ME, isRememberClicked);
            editor.apply();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();

        sharedPreferences = getSharedPreferences("remember_me", MODE_PRIVATE);
        String username = sharedPreferences.getString("USER_NAME", "");
        boolean isClicked = sharedPreferences.getBoolean(GlobalConstants.REMEMBER_ME, false);
        checkBox.setChecked(isClicked);

        binding.etEmail.setText(username);
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
        editor.putBoolean(GlobalConstants.REMEMBER_ME, isRememberClicked);
        editor.apply();
        navigate();
    }

    public void navigate() {

        Intent intent = new Intent(this, MainActivity.class);
        Bundle bundle = new Bundle();
        bundle.putBoolean(GlobalConstants.GUEST, isGuest);
        intent.putExtras(bundle);
//        intent.putExtra(GlobalConstants.GUEST,isGuest);
        startActivity(intent);
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
        if (compoundButton.getId() == R.id.checkBox) {
            if (isChecked) {
                isRememberClicked = true;
            } else {
                isRememberClicked = false;
                editor = sharedPreferences.edit();
                editor.clear();
                editor.apply();
            }
        }
    }
}