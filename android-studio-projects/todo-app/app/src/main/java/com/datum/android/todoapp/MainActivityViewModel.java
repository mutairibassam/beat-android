package com.datum.android.todoapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.datum.android.todoapp.data.TodoRepository;
import com.datum.android.todoapp.data.TodoTable;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    private TodoRepository todoRepository;
    private LiveData<List<TodoTable>> getAllTasks;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        todoRepository = new TodoRepository(application);
        getAllTasks = todoRepository.getAllTasks();
    }

    public void insert(TodoTable todoTable) {
        todoRepository.addTask(todoTable);
    }

    public LiveData<List<TodoTable>> getAllTasks() {
        return getAllTasks;
    }
}
