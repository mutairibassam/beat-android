package com.datum.android.userinteractionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    TextView mName, mEmail, mJobTitle, mMobile, mSkills;
    ImageView mImageGender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        mName = findViewById(R.id.username);
        mEmail = findViewById(R.id.usermail);
        mJobTitle = findViewById(R.id.job_title);
        mMobile = findViewById(R.id.mobile);
        mSkills = findViewById(R.id.skills);

        mImageGender = findViewById(R.id.imgView);

        getData();

    }

    public void getData() {
        Intent intent = getIntent();
        if(intent != null) {
            String sName = intent.getStringExtra(GlobalConstants.USERNAME);
            String sEmail = intent.getStringExtra(GlobalConstants.USER_EMAIL);
            boolean isMale = intent.getBooleanExtra(GlobalConstants.GENDER, true);
            String sJobTitle = intent.getStringExtra(GlobalConstants.JOB_TITLE);
            String sMobile = intent.getStringExtra(GlobalConstants.MOBILE);

            ArrayList<String> skills = intent.getStringArrayListExtra("skills");

            mName.setText(sName);
            mEmail.setText(sEmail);
            mJobTitle.setText(sJobTitle);
            mMobile.setText(sMobile);

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
}