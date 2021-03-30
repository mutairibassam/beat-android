package com.datum.android.todoapp2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.datum.android.todoapp2.adapter.TaskAdapter;
import com.datum.android.todoapp2.databinding.ActivityMainBinding;
import com.datum.android.todoapp2.taskbatabase.TaskTable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    private List<TaskTable> taskTableList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        viewModel = new MainActivityViewModel(getApplication());
        binding.floatBtn.setOnClickListener(View -> {
            startActivity(new Intent(MainActivity.this, AddActivity.class));
        });

        RecyclerView recyclerView = findViewById(R.id.rv_task_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        TaskAdapter taskAdapter = new TaskAdapter(taskTableList);
        recyclerView.setAdapter(taskAdapter);

        viewModel.getAllTasks().observe(this, taskTables -> {
            taskTableList.clear();
            taskTableList.addAll(taskTables);
            taskAdapter.notifyDataSetChanged();
        });


    }

}