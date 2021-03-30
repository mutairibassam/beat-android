package com.datum.android.todoapp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.datum.android.todoapp2.taskbatabase.TaskRepository;
import com.datum.android.todoapp2.taskbatabase.TaskTable;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {

    TaskRepository taskRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
    }

    public LiveData<List<TaskTable>> getAllTasks() {
        return taskRepository.getAllTasks();
    }

    public void delete(TaskTable taskTable) {
        taskRepository.delete(taskTable);
    }

}
