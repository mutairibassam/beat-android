package com.datum.android.userinteractionapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    SwitchCompat mSwitch;
    EditText mName, mEmail, mMobile, mJobTitle;
    Spinner mSpinnerDay, mSpinnerTime;
    RadioGroup mRadioGroup;
    RadioButton mMale, mFemale;
    Button mSave;

    CheckBox mPython, mJava, mSql, mFlask, mXml, mDjango, mJS;
    ArrayList<String> skills;

    boolean isMale = true;
    String sName, sEmail, sMobile, sJobTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInit();

        mSwitch.setOnClickListener(this);
        mSave.setOnClickListener(this);

        mRadioGroup.setOnCheckedChangeListener(this);

    }

    private void getInit() {
        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mSwitch = findViewById(R.id.btn_switch);
        mMobile = findViewById(R.id.txt_mobile);
        mJobTitle = findViewById(R.id.job_title);
        mSpinnerDay = findViewById(R.id.spinner3);
        mSpinnerTime = findViewById(R.id.spinner4);
        mRadioGroup = findViewById(R.id.radioGroup);
        mMale = findViewById(R.id.radioMale);
        mFemale = findViewById(R.id.radioFemail);

        mSave = findViewById(R.id.btn_save);

        checkboxInit();
        hideViews();
        getSpinnerData();
    }

    private void checkboxInit() {
        mPython = findViewById(R.id.python);
        mJava = findViewById(R.id.java);
        mSql = findViewById(R.id.sql);
        mFlask = findViewById(R.id.flask);
        mXml = findViewById(R.id.xml);
        mDjango = findViewById(R.id.django);
        mJS = findViewById(R.id.js);

        skills = new ArrayList<>();
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
        mJobTitle.setVisibility(View.GONE);
    }

    private void getData() {
        sName = mName.getText().toString();
        sEmail = mEmail.getText().toString();
        sMobile = mMobile.getText().toString();
        sJobTitle = mJobTitle.getText().toString();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_switch:
                if(mSwitch.isChecked()) {
                    mMobile.setVisibility(View.VISIBLE);
                    mJobTitle.setVisibility(View.VISIBLE);
                } else {
                    mMobile.setVisibility(View.GONE);
                    mJobTitle.setVisibility(View.GONE);
                }
                break;

            case R.id.btn_save:
                getData();
                navigate();
                break;
        }

    }

    private void navigate() {
        if(sName.equals("")) {
            Toast.makeText(this, "Please fill your name", Toast.LENGTH_LONG).show();
            return;
        }
        if(sEmail.equals("")) {
            new AlertDialog.Builder(this)
                    .setTitle("Missing fields")
                    .setMessage("Email field is required")
                    .setPositiveButton("Understand", null)
                    .show();
            return;
        }

        Intent intent = new Intent(this, Profile.class);
        intent.putExtra(GlobalConstants.USERNAME, sName);
        intent.putExtra(GlobalConstants.USER_EMAIL, sEmail);
        intent.putExtra(GlobalConstants.GENDER, isMale);
        intent.putExtra(GlobalConstants.MOBILE, sMobile);
        intent.putExtra(GlobalConstants.JOB_TITLE, sJobTitle);
        intent.putStringArrayListExtra("skills", skills);
        startActivity(intent);
    }

    /**
     * Method: onCheckedChanged
     *
     * Desc: used to specify the user gender
     *
     */
    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
        if (checkedId == R.id.radioFemail) isMale = false;
        else isMale = true;
    }


    public void onCheckboxClicked(View view) {

        boolean checked = ((CheckBox) view).isChecked();

        switch (view.getId()) {
            case R.id.python:
                if(checked) skills.add("Python");
                else skills.remove("Python");
                break;

            case R.id.java:
                if(checked) skills.add("Java");
                else skills.remove("Java");
                break;

            case R.id.sql:
                if(checked) skills.add("SQL");
                else skills.remove("SQL");
                break;

            case R.id.flask:
                if(checked) skills.add("Flask");
                else skills.remove("Flask");
                break;

            case R.id.xml:
                if(checked) skills.add("XML");
                else skills.remove("XML");
                break;

            case R.id.django:
                if(checked) skills.add("Django");
                else skills.remove("Django");
                break;

            case R.id.js:
                if(checked) skills.add("JavaScript");
                else skills.remove("JavaScript");
                break;
        }
    }
}