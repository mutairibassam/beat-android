package com.datum.android.workerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkContinuation;
import androidx.work.WorkManager;
import androidx.work.Worker;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        TextView textView = findViewById(R.id.textView_two);
        Button imageOne = findViewById(R.id.image_one);

        imageOne.setOnClickListener(View -> {
            // trigger an event
            Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

            WorkManager.getInstance(this)
                    .beginWith(
                            viewModel.compressImage()
                    )
                    .then(
                            viewModel.uploadImage()
                    ).enqueue();

//            viewModel.counter();

            // update UI
//            viewModel.getCounter().observe(this, new Observer<String>() {
//                @Override
//                public void onChanged(String s) {
//                    Log.d(TAG, "onChanged() called with: s = [" + s + "]");
//                    textView.setText(s);
//                }
//            });
        });

        Button imageTwo = findViewById(R.id.image_two);
        imageTwo.setOnClickListener(View -> {
            // trigger an event
            viewModel.counter();

            // update UI
            viewModel.getCounter().observe(this, new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    textView.setText(s);
                }
            });


        });

        Button imageThree = findViewById(R.id.image_three);
        imageThree.setOnClickListener(View -> {
            // trigger an event

            // update UI


        });

        Button imageFour = findViewById(R.id.image_four);
        imageFour.setOnClickListener(View -> {
            // trigger an event

            // update UI


        });

    }


}