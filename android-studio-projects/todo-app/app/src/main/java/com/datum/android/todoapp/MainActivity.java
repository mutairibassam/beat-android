package com.datum.android.todoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.datum.android.todoapp.data.TodoDatabase;
import com.datum.android.todoapp.data.TodoRepository;
import com.datum.android.todoapp.data.TodoTable;
import com.datum.android.todoapp.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // Example to run the database on the main thread
//        TodoDatabase db = TodoDatabase.getInstance(this);
//        db.todoDao().addTask(new TodoTable("Bassam"));


//        binding.add.setOnClickListener(View -> {
//            TodoTable todoTable = new TodoTable("my first task");
//            viewModel.insert(todoTable);
//
//        });

    }
}