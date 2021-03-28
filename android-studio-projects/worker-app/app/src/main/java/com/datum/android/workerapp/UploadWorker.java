package com.datum.android.workerapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class UploadWorker extends Worker {

    private static final String TAG = UploadWorker.class.getSimpleName();

    public UploadWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);

    }

    @NonNull
    @Override
    public Result doWork() {

        Log.d(TAG, "doWork() Upload worker called");

        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "download: " + i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "doWork: " + Result.success());

        return Result.success();
    }

}
