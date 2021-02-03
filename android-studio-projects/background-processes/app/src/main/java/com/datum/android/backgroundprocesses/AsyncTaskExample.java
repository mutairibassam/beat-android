package com.datum.android.backgroundprocesses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.datum.android.backgroundprocesses.databinding.ActivityAsyncTaskExampleBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncTaskExample extends AppCompatActivity {

    private ActivityAsyncTaskExampleBinding binding;

    TextView tv;
    ProgressBar pg;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAsyncTaskExampleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        button = binding.button2;
        tv = binding.textView2;
        pg = binding.progressBar;

        binding.textView2.setVisibility(View.GONE);
        binding.progressBar.setVisibility(View.GONE);


        binding.button2.setOnClickListener(View -> {


            binding.button2.setText("Loading...");
            pg.setProgress(0);
            tv.setText(binding.progressBar.getProgress() + "/" + 100);
            TestAsync runningTask = new TestAsync();
            runningTask.execute();

        });

    }

    class TestAsync extends AsyncTask<String, Integer, Void> {


        protected void onPreExecute() {
            super.onPreExecute();
            pg.setProgress(0);
            tv.setVisibility(View.VISIBLE);
            pg.setVisibility(View.VISIBLE);
        }

        protected Void doInBackground(String... arg0) {

            for (int i = 0; i < 10 ; i++) {
                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                publishProgress();
//                publishProgress(10);

            }

            return null;
        }

        protected void onProgressUpdate(Integer... a) {
            super.onProgressUpdate(a);

            binding.progressBar.incrementProgressBy(10);
//            binding.progressBar.incrementProgressBy(a[0]);

            binding.textView2.setText(binding.progressBar.getProgress() + "/" + 100);

        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            button.setText("Execute");
        }

    }
}
