package com.datum.android.livedataapp;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Random;

public class MainActivityViewModel extends ViewModel {

    // mutable live data should be only visible to our view model class
    private MutableLiveData<String> myRandom;

    // for public use cases we create a custom public method that return our mutable data as live data type
    public LiveData<String> getRandom() {
        Log.d("TAG", "getRandom: ");
        if(myRandom == null) {
            myRandom = new MutableLiveData<String>();
            generateRandomNumber();
        }
        return myRandom;
    }

    public void generateRandomNumber() {
        Log.d("TAG", "generateRandomNumber: ");
        Random random = new Random();
        myRandom.setValue("" + random.nextInt(10 - 1)+1);
    }
}
