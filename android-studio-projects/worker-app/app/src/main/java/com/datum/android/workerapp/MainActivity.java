package com.datum.android.workerapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    // Define the parameter keys:
    public static final String KEY_X_ARG = "X";
    public static final String KEY_Y_ARG = "Y";
    public static final String KEY_Z_ARG = "Z";
    // ...and the result key:
    public static final String KEY_RESULT = "result";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        Button uploadBtn = findViewById(R.id.btn_upload);

        uploadBtn.setOnClickListener(View -> {
            // trigger an event
            Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");


            OneTimeWorkRequest compress = viewModel.compressImage();
            OneTimeWorkRequest upload = viewModel.uploadImage();

            WorkManager.getInstance(this)
                    .beginWith(
                            compress
                    )
                    .then(
                            upload
                    )
                    .enqueue();

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(compress.getId())
                    .observe(this, info -> {

                        // to get the output -> int res = info.getOutputData().getInt(KEY_RESULT, 0);
                        TextView textView = findViewById(R.id.tv_compress);
                        textView.setText(info.getState().toString());

                    });

            WorkManager.getInstance(this).getWorkInfoByIdLiveData(upload.getId())
                    .observe(this, workInfo -> {

                        TextView textView = findViewById(R.id.tv_upload);
                        textView.setText(workInfo.getState().toString());
                    });

        });

        Button downloadBtn = findViewById(R.id.btn_download);
        downloadBtn.setOnClickListener(View -> {

            PeriodicWorkRequest download = viewModel.downloadImage();
            WorkManager.getInstance(this).enqueue(download);

//            /**
//             * There is no output for Periodic work manager request, since the worker will reuse the same ID for each run
//             *
//             */
//
//            WorkManager.getInstance(this).getWorkInfoByIdLiveData(download.getId())
//                    .observe(this, workInfo -> {
//                        TextView textView = findViewById(R.id.tv_download);
//
//                        Log.d(TAG, "onCreate: " + workInfo.getOutputData().getString("result"));
//                        textView.setText(workInfo.getOutputData().getString("result"));
//                    });
        });


    }
}