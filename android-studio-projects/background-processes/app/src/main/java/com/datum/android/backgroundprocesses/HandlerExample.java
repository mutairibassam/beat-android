package com.datum.android.backgroundprocesses;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.datum.android.backgroundprocesses.databinding.ActivityHandlerExampleBinding;

public class HandlerExample extends AppCompatActivity {

    public static final String TAG = "HandlerExample";

    private ActivityHandlerExampleBinding binding;

    private MyHandler handler;

    TextView status;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHandlerExampleBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        /**
         * Help to communicate between more than 2 threads
         *
         */

//        status = findViewById(R.id.tv_status_handler);
//
//        handler = new MyHandler();
//
//        binding.button2.setOnClickListener(View -> {
//            String name = binding.etName.getText().toString();
//            threadOne(name);
//
//        });


    }


    private void threadOne(String s) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Message message = new Message();
                message.obj = s;
                handler.sendMessage(message);
            }
        });
        thread.start();
}




    class MyHandler extends Handler {

        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            status.setText(String.valueOf(msg.obj));

        }

    }
}