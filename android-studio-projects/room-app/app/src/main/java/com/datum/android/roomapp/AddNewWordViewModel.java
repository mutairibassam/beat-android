package com.datum.android.roomapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AddNewWordViewModel extends AndroidViewModel {

    private WordRepository mRepository;


    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
    }

    public void insert(Words word) {
        mRepository.insert(word);
    }

    public void update(Words word) {
        mRepository.update(word);
    }
}
