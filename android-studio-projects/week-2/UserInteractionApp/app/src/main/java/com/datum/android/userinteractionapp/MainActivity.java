package com.datum.android.userinteractionapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener,
        AdapterView.OnItemSelectedListener, SeekBar.OnSeekBarChangeListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    SwitchCompat mSwitch;
    EditText mName, mEmail, mMobile, mJobTitle;
    Spinner mSpinnerDay, mSpinnerTime;
    RadioGroup mRadioGroup;
    RadioButton mMale, mFemale;
    Button mSave;
    SeekBar seekBar;
    TextView textView;

    ArrayList<String> skills = new ArrayList<>();

    boolean isMale = true;
    String sName, sEmail, sMobile, sJobTitle, sDay, sTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getInit();

        mSwitch.setOnClickListener(this);
        mSave.setOnClickListener(this);

        mRadioGroup.setOnCheckedChangeListener(this);
        mSpinnerDay.setOnItemSelectedListener(this);
        mSpinnerTime.setOnItemSelectedListener(this);
        seekBar.setOnSeekBarChangeListener(this);

    }

    private void getInit() {
        mName = findViewById(R.id.name);
        mEmail = findViewById(R.id.email);
        mSwitch = findViewById(R.id.btn_switch);
        mMobile = findViewById(R.id.txt_mobile);
        mJobTitle = findViewById(R.id.job_title);
        mSpinnerDay = findViewById(R.id.day_spinner);
        mSpinnerTime = findViewById(R.id.time_spinner);
        mRadioGroup = findViewById(R.id.radioGroup);
        mMale = findViewById(R.id.radioMale);
        mFemale = findViewById(R.id.radioFemail);
        seekBar = findViewById(R.id.seekBar);
        textView = findViewById(R.id.textView);

        mSave = findViewById(R.id.btn_save);

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
        intent.putStringArrayListExtra(GlobalConstants.SKILLS, skills);
        intent.putExtra(GlobalConstants.DAY, sDay);
        intent.putExtra(GlobalConstants.TIME, sTime);
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int pos, long l) {
        switch (adapterView.getId()) {
            case R.id.day_spinner:
                sDay = adapterView.getItemAtPosition(pos).toString();
                break;

            case R.id.time_spinner:
                sTime = adapterView.getItemAtPosition(pos).toString();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progressValue, boolean b) {
        textView.setTextSize((float)(progressValue) + 25);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }


}