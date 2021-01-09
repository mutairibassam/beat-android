package com.datum.android.recyclerviewapp.launcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.datum.android.recyclerviewapp.R;
import com.datum.android.recyclerviewapp.newlayout.NewMainActivity;
import com.datum.android.recyclerviewapp.oldlayout.OldMainActivity;

public class Launcher extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

    }

    public void navigateToOld(View view) {

        startActivity(new Intent(this, OldMainActivity.class));
    }

    public void navigateToNew(View view) {

        startActivity(new Intent(this, NewMainActivity.class));
    }

}