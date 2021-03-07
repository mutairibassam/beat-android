package com.datum.android.todoapp.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TodoRepository {

    private TodoDao todoDao;

    public TodoRepository(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    LiveData<List<TodoTable>> getAllTasks = todoDao.getAllTasks();

    public void addTask(TodoTable todoTable) {
        todoDao.addTask(todoTable);
    }
}
