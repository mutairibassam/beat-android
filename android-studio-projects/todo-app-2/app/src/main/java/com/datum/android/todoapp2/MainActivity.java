package com.datum.android.todoapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        View view = findViewById(R.id.main_toolbar);
        ((Toolbar) view).setTitle("To-Do List");
        setSupportActionBar((Toolbar) view);

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

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                viewModel.delete(taskAdapter.getTaskAt(pos));
            }
        }).attachToRecyclerView(recyclerView);

    }

}