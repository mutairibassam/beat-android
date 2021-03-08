package com.datum.android.livedataapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.datum.android.livedataapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        /**
         *  What is LiveData?
         *  -   an observable data holder class
         *  -   lifecycle aware which means respects the lifecycle of other app components "Activity, Fragment, Services"
         *      to ensure LiveData update only app component observers that in active lifecycle state
         *
         *
         * @Observable_data_holder_class : means that liveData can be observed by other components like UI controllers
         *
         * @LiveData = data is aware of the LifeCycle of it's observer (Activity/Fragment)
         *
         *
         */


        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        viewModel.getRandom().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String it) {
                Log.d("TAG", "onChanged: ");
                binding.number.setText(it);
            }
        });

        binding.generate.setOnClickListener(View -> {
            Log.d("TAG", "viewModel.generateRandomNumber: ");
            viewModel.generateRandomNumber();
        });


    }
}