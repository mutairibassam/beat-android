package com.datum.android.backgroundprocesses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;

import com.datum.android.backgroundprocesses.databinding.ActivityWithoutAsyncTaskBinding;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Stream;

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
            someDelay();

            doInBackground();

        });

    }

    private void doInBackground() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                // doInBackground() method
                String url = "https://www.json-generator.com/api/json/get/cgcifjsyIy?indent=2";
                myString = getSiteString(url);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        // onPostExecute() method
                        binding.getButton.setText("Received Response");
                        binding.tvResponse.setText(myString);
                    }
                });
            }

        }).start();
    }

    private String getSiteString(String site) {
        String stream = "";

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

    private void someDelay() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        new CountDownTimer(15000, 1000) {
                            public void onTick(long millisUntilFinished) {
                                binding.tvResponse.setText("seconds remaining: " + millisUntilFinished / 1000);
                            }

                            public void onFinish() {
                                binding.tvResponse.setText("done!");
                            }
                        }.start();
                    }
                });
            }
        }).start();



    }

}