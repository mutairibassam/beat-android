package com.datum.android.roomapp;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class AddNewWordViewModel extends AndroidViewModel {

    private WordRepository mRepository;

    WorkManager workManager;
    WorkRequest workRequest;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
    }

    public void insert(Words word) {

//        // Create the Data object:
//        Data myData = new Data.Builder()
//                // We need to pass three integers: X, Y, and Z
//                .putString("KEY_text_ARG", wordText)
//                .putString("KEY_meaning_ARG", meaningText)
//                .putString("KEY_type_ARG", typeText)
//                // ... and build the actual Data object:
//                .build();
//
//        workRequest = new OneTimeWorkRequest.Builder(MyWorker.class)
//                .setInputData(myData)
//                .build();
//
//        WorkManager.getInstance(getApplication().getApplicationContext()).enqueue(workRequest);

    }

    public void update(Words word) {
        mRepository.update(word);
    }
}
