package com.datum.android.workerapp;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.OneTimeWorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.net.NetworkInterface;

public class CompressWorker extends Worker {

    private static final String TAG = CompressWorker.class.getSimpleName();

    public CompressWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        Log.d(TAG, "doWork() Compress called");

        for (int i = 0; i <= 3; i++) {
            try {
                Thread.sleep(1000);
                Log.d(TAG, "compress: " + i);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Fetch the arguments (and specify default values):
        int x = getInputData().getInt(MainActivityViewModel.KEY_X_ARG, 0);
        int y = getInputData().getInt(MainActivityViewModel.KEY_Y_ARG, 0);
        int z = getInputData().getInt(MainActivityViewModel.KEY_Z_ARG, 0);

        int result = x + y + z;

        //...set the output, and we're done!
        Data output = new Data.Builder()
                .putInt(MainActivityViewModel.KEY_RESULT, result)
                .build();

        Log.d(TAG, "doWork() result ==> " + output);
        return Result.success();
    }

}
