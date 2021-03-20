package com.datum.android.todoapp.data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.Worker;

import java.util.List;

public class TodoRepository {

    private TodoDao todoDao;
    private LiveData<List<TodoTable>> getAllTasks;

    public void addTask(TodoTable todoTable) {
        Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                todoDao.addTask(todoTable);
            }

        });

        myThread.start();

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
