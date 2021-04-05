package com.datum.android.todoapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;

import com.datum.android.todoapp2.databinding.ActivityAddBinding;
import com.datum.android.todoapp2.taskbatabase.TaskTable;

public class AddActivity extends AppCompatActivity {

    private ActivityAddBinding binding;
    private AddActivityViewModel viewModel;

    boolean isSelected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new AddActivityViewModel(getApplication());


        binding.addButton.setOnClickListener(View -> {
            String userInput = binding.etTask.getText().toString();
            TaskTable newTask = new TaskTable(userInput, isSelected);
            viewModel.insertTask(newTask);
            finish();
        });

        binding.favButton.setOnClickListener(View -> {
            if(!isSelected) {
                binding.favButton.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_favorite_selected));
                isSelected = true;
            } else {
                binding.favButton.setBackground(ContextCompat.getDrawable(this, R.drawable.ic_favorite_unselected));
                isSelected = false;
            }
        });

    }
}