package com.datum.android.backgroundprocesses;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;

import com.datum.android.backgroundprocesses.databinding.ActivityWithoutAsyncTaskExampleBinding;

import java.util.ArrayList;

public class WithoutAsyncTaskExample extends AppCompatActivity {

    private ActivityWithoutAsyncTaskExampleBinding binding;

    ArrayList<String> arr = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityWithoutAsyncTaskExampleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.blockMainButton.setOnClickListener(View -> {
            try {
                Thread.sleep(10000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            tvShared("Released");
        });

        binding.threadOneButton.setOnClickListener(View -> {
            new Thread(new Runnable() {
                @Override
                public void run() {


                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            binding.tvStatusOne.setText("Started Execution!");
//                            binding.tvShared.setText("Thread one is executing!");
                            tvShared("Thread one is executing");
                            new CountDownTimer(75000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    binding.tvThreadOneWithout.setText("" + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    binding.tvStatusOne.setText("completed!");
                                }
                            }.start();

                        }
                    });

                }
            }).start();
        });


        binding.threadTwoButton.setOnClickListener(View -> {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            binding.tvStatusTwo.setText("Started Execution!");
//                            binding.tvShared.setText("Thread two is executing!");
                            tvShared("Thread two is executing");
                            new CountDownTimer(50000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    binding.tvThreadTwoWithout.setText("" + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    binding.tvStatusTwo.setText("completed!");
                                }
                            }.start();
                        }
                    });
                }
            }).start();
        });

        binding.threadThreeButton.setOnClickListener(View -> {
            new Thread(new Runnable() {
                @Override
                public void run() {

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            binding.tvStatusThree.setText("Started Execution!");
//                            binding.tvShared.setText("Thread three is executing!");
                            tvShared("Thread three is executing");
                            new CountDownTimer(30000, 1000) {
                                public void onTick(long millisUntilFinished) {
                                    binding.tvThreadThreeWithout.setText("" + millisUntilFinished / 1000);
                                }

                                public void onFinish() {
                                    binding.tvStatusThree.setText("completed!");
                                }
                            }.start();

                        }
                    });
                }
            }).start();
        });


    }

    public void tvShared(String msg) {
        StringBuilder sb = new StringBuilder();
        arr.add(msg);
        for (int i = 0; i < arr.size(); i++) {
            sb.append(arr.get(i));
            sb.append("\n");
        }

        binding.tvShared.setText(sb.toString());

    }
}