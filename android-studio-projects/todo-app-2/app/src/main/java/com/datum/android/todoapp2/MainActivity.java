package com.datum.android.todoapp2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.datum.android.todoapp2.adapter.TaskAdapter;
import com.datum.android.todoapp2.databinding.ActivityMainBinding;
import com.datum.android.todoapp2.taskbatabase.TaskTable;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;

    private List<TaskTable> taskTableList = new ArrayList<>();

    private TaskAdapter taskAdapter;

    @Override
    public boolean onQueryTextSubmit(String query) {
        if(query != null) {
            searchDatabase(query);
        }
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        if(newText != null) {
            searchDatabase(newText);
        }
        return true;
    }

    public void searchDatabase(String query) {
        String searchQuery = "%"+query+"%";

        viewModel.getSearchTask(searchQuery).observe(this, new Observer<List<TaskTable>>() {
            @Override
            public void onChanged(List<TaskTable> taskTables) {
                taskAdapter.setTaskList(taskTables);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
            searchView.setIconifiedByDefault(false);
            searchView.setOnQueryTextListener(this);

        }
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

        taskAdapter = new TaskAdapter(taskTableList);
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