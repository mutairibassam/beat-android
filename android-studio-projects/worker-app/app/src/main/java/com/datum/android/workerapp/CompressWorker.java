package com.datum.android.workerapp;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

public class CompressWorker extends Worker {

    private static final String TAG = CompressWorker.class.getSimpleName();

    public CompressWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.d(TAG, "doWork() Compress called");

        for (int i = 0; i <= 10; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "compress: " + i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.d(TAG, "doWork: " + Result.success());

        return Result.success();
    }
}
