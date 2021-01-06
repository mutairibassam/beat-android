package com.datum.android.userinteractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    TextView mName, mEmail, mJobTitle, mMobile, mSkills, mDay, mTime;
    TextView txtSkills, txtJobtitle, txtMobile, txtDay, txtTime;

    ImageView mImageGender;

    String sName, sEmail, sJobTitle, sMobile, sDay, sTime;
    boolean isMale;
    ArrayList<String> skills = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getInit();
        getData();

    }

    private void getInit() {
        mName = findViewById(R.id.username);
        mEmail = findViewById(R.id.usermail);
        mJobTitle = findViewById(R.id.job_title);
        mMobile = findViewById(R.id.mobile);
        mSkills = findViewById(R.id.skills);
        mDay = findViewById(R.id.day);
        mTime = findViewById(R.id.time);

        txtJobtitle = findViewById(R.id.txt_jobtitle);
        txtSkills = findViewById(R.id.txt_skills);
        txtMobile = findViewById(R.id.txt_mobile);
        txtDay = findViewById(R.id.txt_day);
        txtTime = findViewById(R.id.txt_time);

        mImageGender = findViewById(R.id.imgView);
    }

    public void getData() {
        Intent intent = getIntent();
        if(intent != null) {
            sName = intent.getStringExtra(GlobalConstants.USERNAME);
            sEmail = intent.getStringExtra(GlobalConstants.USER_EMAIL);
            isMale = intent.getBooleanExtra(GlobalConstants.GENDER, true);
            sJobTitle = intent.getStringExtra(GlobalConstants.JOB_TITLE);
            sMobile = intent.getStringExtra(GlobalConstants.MOBILE);
            skills = intent.getStringArrayListExtra(GlobalConstants.SKILLS);
            sDay = intent.getStringExtra(GlobalConstants.DAY);
            sTime = intent.getStringExtra(GlobalConstants.TIME);

            prepareLayout();
            setValues();

        }
    }

    private void prepareLayout() {
        if(sMobile.equals(""))  {
            mMobile.setVisibility(View.GONE);
            txtMobile.setVisibility(View.GONE);
        } else {
            mMobile.setVisibility(View.VISIBLE);
            txtMobile.setVisibility(View.VISIBLE);
        }
        if(sJobTitle.equals("")) {
            mJobTitle.setVisibility(View.GONE);
            txtJobtitle.setVisibility(View.GONE);
        } else {
            mJobTitle.setVisibility(View.VISIBLE);
            txtJobtitle.setVisibility(View.VISIBLE);
        }
        if(skills.size() == 0) {
            mSkills.setVisibility(View.GONE);
            txtSkills.setVisibility(View.GONE);
        } else {
            mSkills.setVisibility(View.VISIBLE);
            txtSkills.setVisibility(View.VISIBLE);
        }

    }

    public void setValues() {
        mName.setText(sName);
        mEmail.setText(sEmail);
        mJobTitle.setText(sJobTitle);
        mMobile.setText(sMobile);
        mDay.setText(sDay);
        mTime.setText(sTime);

        StringBuilder sb = new StringBuilder();
        if(skills != null) {
            for(String skill : skills) {
                sb.append(skill).append("\n");
            }
            mSkills.setText(sb.toString());
        }

        if(isMale) mImageGender.setImageResource(R.drawable.ic_male);
        else mImageGender.setImageResource(R.drawable.ic_femenine);
    }
}