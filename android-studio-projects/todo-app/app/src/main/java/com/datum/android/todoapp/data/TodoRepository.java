package com.datum.android.todoapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.Worker;

import com.datum.android.todoapp.AppExecutor;

import java.util.List;

public class TodoRepository {

    private TodoDao todoDao;
    private LiveData<List<TodoTable>> getAllTasks;

    public void addTask(TodoTable todoTable) {

        AppExecutor.getInstance().getNetworkIO().execute(() -> {
            todoDao.addTask(todoTable);
        });
    }

    public LiveData<List<TodoTable>> getAllTasks() {
        return getAllTasks;
    }

    public TodoRepository(Application application) {
        TodoDatabase db = TodoDatabase.getInstance(application);
        todoDao = db.todoDao();
        getAllTasks = todoDao.getAllTasks();
    }

}
