package com.datum.android.mvvm;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    int number = 0;

    public void add() {
        number++;
    }
}
