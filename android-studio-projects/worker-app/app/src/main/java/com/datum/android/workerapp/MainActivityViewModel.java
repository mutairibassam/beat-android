package com.datum.android.workerapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.Constraints;
import androidx.work.Data;
import androidx.work.NetworkType;
import androidx.work.OneTimeWorkRequest;
import androidx.work.PeriodicWorkRequest;

import java.util.concurrent.TimeUnit;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

    // Define the parameter keys:
    public static final String KEY_X_ARG = "X";
    public static final String KEY_Y_ARG = "Y";
    public static final String KEY_Z_ARG = "Z";
    // ...and the result key:
    public static final String KEY_RESULT = "result";

    private MutableLiveData<String> getCount;

    public LiveData<String> getCounter() {
        if(getCount == null) {
            getCount = new MutableLiveData<String>();
            counter();
        }
        return getCount;
    }

    public OneTimeWorkRequest uploadImage() {
        return new OneTimeWorkRequest.Builder(UploadWorker.class).build();
    }

    public OneTimeWorkRequest compressImage() {
        // Create the Data object:
        Data myData = new Data.Builder()
                // We need to pass three integers: X, Y, and Z
                .putInt(KEY_X_ARG, 2)
                .putInt(KEY_Y_ARG, 2)
                .putInt(KEY_Z_ARG, 2)
                // ... and build the actual Data object:
                .build();

        return new OneTimeWorkRequest.Builder(CompressWorker.class).setInputData(myData).build();
    }

    public void counter() {
        AppExecutor.getInstance().getNetworkIO().execute(() -> {
            for (int i = 0; i <= 3; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d(TAG, "counter: " + i);
                getCount.postValue("" + i);
            }
        });

    }

    public PeriodicWorkRequest downloadImage() {

        final String TAG = "download";

        Constraints constraints = new Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build();

        PeriodicWorkRequest period = new PeriodicWorkRequest.Builder(
                DownloadWorker.class,
                15,
                TimeUnit.MINUTES,
                17,
                TimeUnit.MINUTES)
                .addTag(TAG)
                .setConstraints(constraints)
                .build();

        return period;
    }

}
