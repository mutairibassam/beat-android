package com.datum.android.workerapp;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class DownloadWorker extends Worker {

    private static final String TAG = DownloadWorker.class.getSimpleName();


    public DownloadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }


    @NonNull
    @Override
    public Result doWork() {

        /**
         * There is no output for Periodic work manager request, since the worker will reuse the same ID for each run
         *
         */

        Context context = getApplicationContext();
        for (int i = 0; i <= 5; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "Download: " + i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        AppExecutor.getInstance().getMainThread().execute(() -> {
            Toast.makeText(context, "Test", Toast.LENGTH_SHORT).show();
        });

        return Result.success();
    }
}
