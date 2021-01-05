package com.datum.android.userinteractionapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    SwitchCompat mSwitch;
    EditText mMobile, mEmail;
    Spinner mSpinnerDay, mSpinnerTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInit();

        mSwitch.setOnClickListener(this);
    }

    private void getInit() {
        mSwitch = findViewById(R.id.btn_switch);
        mMobile = findViewById(R.id.mobile);
        mEmail = findViewById(R.id.email);
        mSpinnerDay = findViewById(R.id.spinner3);
        mSpinnerTime = findViewById(R.id.spinner4);

        hideViews();
        getSpinnerData();
    }

    private void getSpinnerData() {

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.time, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSpinnerTime.setAdapter(adapter);

        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this,
                R.array.day, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinnerDay.setAdapter(arrayAdapter);
    }

    private void hideViews() {
        mMobile.setVisibility(View.GONE);
        mEmail.setVisibility(View.GONE);
    }


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btn_switch:
                if(mSwitch.isChecked()) {
                    mMobile.setVisibility(View.VISIBLE);
                    mEmail.setVisibility(View.VISIBLE);
                } else {
                    mMobile.setVisibility(View.GONE);
                    mEmail.setVisibility(View.GONE);
                }
                break;
        }

    }
}