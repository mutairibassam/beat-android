package com.datum.android.backgroundprocesses;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.datum.android.backgroundprocesses.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.withoutAsynctaskButton.setOnClickListener(View -> {
            startActivity(new Intent(MainActivity.this, WithoutAsyncTaskExample.class));

        });

        binding.asyncTaskButton.setOnClickListener(View -> {
            startActivity(new Intent(MainActivity.this, AsyncTaskExample.class));

        });

        binding.downloadButton.setOnClickListener(View -> {
            startActivity(new Intent(MainActivity.this, WithoutAsyncTaskDownloadExample.class));

        });
    }
}