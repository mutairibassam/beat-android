package com.datum.android.todoapp2;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.todoapp2.adapter.TaskAdapter;
import com.datum.android.todoapp2.databinding.ActivityMainBinding;
import com.datum.android.todoapp2.taskbatabase.TaskTable;
import com.datum.android.todoapp2.util.AppExecutor;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private ActivityMainBinding binding;
    private MainActivityViewModel viewModel;
    private RecyclerView recyclerView;

    private List<TaskTable> taskTableList = new ArrayList<>();
    List<TaskTable> favList = new ArrayList<>();

    private TaskAdapter taskAdapter;
    boolean clicked = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getInit();

        viewModel = new MainActivityViewModel(getApplication());
        binding.floatBtn.setOnClickListener(View -> {
            startActivity(new Intent(MainActivity.this, AddActivity.class));
        });

        viewModel.getAllTasks().observe(this, taskTables -> {
            taskAdapter.setTaskList(taskTables);
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

    private void getInit() {
        View view = findViewById(R.id.main_toolbar);
        Toolbar customToolbar = (Toolbar) view;
        customToolbar.setTitle("");
        setSupportActionBar(customToolbar);

        recyclerView = findViewById(R.id.rv_task_id);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /**
         *      we listen to the adapter and update the fav status based on the user click
         *
         */
        taskAdapter = new TaskAdapter(taskTableList, taskTable ->
                viewModel.insertTask(taskTable));

        recyclerView.setAdapter(taskAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setIconifiedByDefault(false);
        searchView.setOnQueryTextListener(this);

        return true;
    }

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

        viewModel.getSearchTask(searchQuery).observe(this, taskTables ->
                taskAdapter.setTaskList(taskTables));
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.sort:
                doSort();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void doSort() {

        if(clicked) {
            favList.clear();
            AppExecutor.getInstance().getNetworkIO().execute(() ->
                    favList.addAll(viewModel.getFavTask()));
            taskAdapter.setTaskList(favList);

        } else {
            viewModel.getAllTasks().observe(this, taskTables ->
                    taskAdapter.setTaskList(taskTables));
        }
        clicked = !clicked;

    }
}