package com.datum.android.sharedpreferences;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.telephony.emergency.EmergencyNumber;
import android.view.View;
import android.widget.Toast;

import com.datum.android.sharedpreferences.databinding.ActivityExternalStorageBinding;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class ExternalStorage extends AppCompatActivity {

    private ActivityExternalStorageBinding binding;

    private static final String TAG = ExternalStorage.class.getSimpleName();
    public static final int WRITE_EXTERNAL_REQUEST_CODE = 1001;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExternalStorageBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        // getPermission();
        
        binding.newMemoButton.setOnClickListener(View -> {
            createFile();
        });

        binding.deleteButton.setOnClickListener(View -> {

            // used only for external application level
            // > 4.4
//            getExternalFilesDir();

            // < 4.4
//            ContextCompat.getExternalFilesDirs();

            String file = binding.etMemo.getText().toString();
            deleteFiles(file);

            binding.etMemo.setText("");
        });

        binding.previousMemoButton.setOnClickListener(View -> fetchFiles());

    }

    private void fetchFiles() {
        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(root);
        for (int i = 0; i < file.length(); i++) {
            binding.tvMemo.setText(file.getName());

        }

    }

    private void deleteFiles(String fileName) {

        String root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).toString();
        File file = new File(root + "/" + fileName);
        file.delete();

    }

    private void getPermission() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            String[] permissions = new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, WRITE_EXTERNAL_REQUEST_CODE);
        }
    }


    public boolean isExternalStorageWritable() {

        getPermission();

        String state = Environment.getExternalStorageState();

        // MEDIA_MOUNTED == can read/write
        if(state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();

        if(state.equals(Environment.MEDIA_MOUNTED) || state.equals(Environment.MEDIA_MOUNTED_READ_ONLY)) {
            return true;
        }
        return false;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case WRITE_EXTERNAL_REQUEST_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(this, "Permission has been granted", Toast.LENGTH_SHORT).show();

                }
                return;
        }

    }

    public void createFile() {

        if(isExternalStorageWritable()) {
            // getExternalStoragePublicDirectory == for external ( you need to specify the path for the external file )
            // getExternalStorageDirectory == for internal
            File root = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            String fileName = binding.etMemo.getText().toString();
            File file = new File(root, fileName);
            try {
                file.createNewFile();
                Toast.makeText(this, "A new file with name " + fileName + " has been created successfully", Toast.LENGTH_SHORT).show();
                binding.etMemo.setText("");

            } catch (IOException e) {
                e.printStackTrace();
            }

            // make a new mkdir == in case you want nested directories
//             file.mkdir();
        }

    }

}
