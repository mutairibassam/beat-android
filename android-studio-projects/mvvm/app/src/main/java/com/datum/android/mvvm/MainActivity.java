package com.datum.android.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;

import com.datum.android.mvvm.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        MainActivityViewModel viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);

        setNumber(viewModel);

        binding.button.setOnClickListener(View -> {
            viewModel.add();
            setNumber(viewModel);
        });

    }

    private void setNumber(MainActivityViewModel viewModelJava) {
        binding.textView.setText(String.valueOf(viewModelJava.number));
    }

}