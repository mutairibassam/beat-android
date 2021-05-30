package com.datum.android.todoapp2.taskbatabase;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.datum.android.todoapp2.util.AppExecutor;

import java.util.List;

public class TaskRepository {

    TaskDao taskDao;
    TaskDatabase taskDatabase;

    public TaskRepository(Application application) {
        taskDatabase = TaskDatabase.getInstance(application);
        taskDao = taskDatabase.taskDao();
    }


    public void insertTask(TaskTable taskTable) {
        AppExecutor.getInstance().getNetworkIO().execute( () -> {
            taskDao.insertTask(taskTable);
        });
    }

    public LiveData<List<TaskTable>> getAllTasks() {
        return taskDao.getAllTasks();
    }

    public void delete(TaskTable taskTable) {
        AppExecutor.getInstance().getNetworkIO().execute( () -> {
            taskDao.delete(taskTable);
        });
    }

    public LiveData<List<TaskTable>> getSearchTasks(String searchTask) {
        return taskDao.getSearchTask(searchTask);
    }

    public List<TaskTable> getFavTasks() {
        return taskDao.getFavTask();
    }


}
