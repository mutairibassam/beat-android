package com.datum.android.backgroundprocesses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.view.View;

import com.datum.android.backgroundprocesses.databinding.ActivityWithoutAsyncTaskBinding;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WithoutAsyncTask extends AppCompatActivity {

    private ActivityWithoutAsyncTaskBinding binding;

    String myString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWithoutAsyncTaskBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.getButton.setOnClickListener(View -> {

            // onPreExecute() method
            binding.getButton.setText("Loading...");

            mainThread();
            threadOne();
            threadTwo();

        });

    }

    private void mainThread() {

        WithoutAsyncTask.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.tvMainStatus.setText("started!");
                new CountDownTimer(10000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        binding.tvResponse.setText("main timer - seconds remaining: " + millisUntilFinished / 1000);
                    }

                    public void onFinish() {
                        binding.tvMainStatus.setText("completed!");
                    }
                }.start();
            }
        });

    }

    private void threadTwo() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                // doInBackground() method
                String url = "https://www.json-generator.com/api/json/get/cgcifjsyIy?indent=2";
                myString = getSiteString(url);

                // will crash the app, since it's required to update the UI
                // and it's executed on background thread
//                binding.tvThreadTwoStatus.setText("started");

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        // onPostExecute() method
                        binding.getButton.setText("Received Response");
                        binding.tvResponse.setText(myString);
                        binding.tvThreadTwoStatus.setText("completed");
                    }
                });
            }

        }).start();
    }

    private String getSiteString(String site) {
        String stream = "";
        binding.tvThreadTwoStatus.setText("started");


        try {
            URL url = new URL(site);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            if(urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "utf-8"));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                    stringBuilder.append("\n");
                }
                stream = stringBuilder.toString();
                urlConnection.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return stream;
    }

    private void threadOne() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        binding.tvThreadOneStatus.setText("started");
                        new CountDownTimer(15000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                binding.tvResponse.setText("seconds remaining: " + millisUntilFinished / 1000);
                            }

                            public void onFinish() {
                                binding.tvThreadOneStatus.setText("completed");
                            }
                        }.start();
                    }
                });
            }
        }).start();



    }



}