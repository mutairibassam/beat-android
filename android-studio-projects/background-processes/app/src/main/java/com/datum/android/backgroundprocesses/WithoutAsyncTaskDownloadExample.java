package com.datum.android.backgroundprocesses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.datum.android.backgroundprocesses.databinding.ActivityWithoutAsyncTaskDownloadExampleBinding;

public class WithoutAsyncTaskDownloadExample extends AppCompatActivity {

    private ActivityWithoutAsyncTaskDownloadExampleBinding binding;

    TextView tv;
    Button button;
    ProgressBar pg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWithoutAsyncTaskDownloadExampleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        tv = binding.textView2;
        button = binding.button2;
        pg = binding.progressBar;

        binding.progressBar.setVisibility(View.GONE);
        binding.textView2.setVisibility(View.GONE);

        button.setOnClickListener(View -> {

            pg.setProgress(0);
            tv.setText(pg.getProgress() + "/" + 100);
            tv.setVisibility(android.view.View.VISIBLE);
            pg.setVisibility(android.view.View.VISIBLE);
            button.setText("Loading...");

            Thread thread = new Thread(() -> {
                for (int i = 0; i < 10; i++) {

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    runOnUiThread(() -> {
                        pg.incrementProgressBy(10);
                        tv.setText(pg.getProgress() + "/" + 100);
                    });


                }

                runOnUiThread(() -> {
                    button.setText("Execute");
                    tv.setText(pg.getProgress() + "/" + 100);
                });
            });

            thread.start();
        });
    }
}