package com.datum.android.workerapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.work.OneTimeWorkRequest;

public class MainActivityViewModel extends ViewModel {

    private static final String TAG = MainActivityViewModel.class.getSimpleName();

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
        return new OneTimeWorkRequest.Builder(CompressWorker.class).build();
    }

    public void counter() {
        AppExecutor.getInstance().getNetworkIO().execute(() -> {
            for (int i = 0; i <= 10; i++) {
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

}
