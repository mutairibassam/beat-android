package com.datum.android.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.datum.android.sharedpreferences.databinding.ActivityInternalStorageBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class InternalStorage extends AppCompatActivity {

    private ActivityInternalStorageBinding binding;

    public static final String FILE_NAME = "MEMO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInternalStorageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);




        binding.newMemoButton.setOnClickListener(View -> {

            try {
                FileOutputStream file = openFileOutput(FILE_NAME, MODE_APPEND);
                PrintWriter pw = new PrintWriter(file);
                pw.println(binding.etMemo.getText().toString());
                pw.close();
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

            binding.etMemo.setText("");
            showMemo();

        });

        binding.previousMemoButton.setOnClickListener(View -> {

            showMemo();

        });

    }

    public void showMemo() {
        try {

            FileInputStream fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);

            String allText = "";
            String temp = "";

            while ((temp = br.readLine()) != null) {
                allText += temp + "\n";

            }

            br.close();
            isr.close();
            fis.close();

            binding.tvMemo.setText(allText);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}