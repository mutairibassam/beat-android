package com.datum.android.todoapp2;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.datum.android.todoapp2.taskbatabase.TaskRepository;
import com.datum.android.todoapp2.taskbatabase.TaskTable;

public class AddActivityViewModel extends AndroidViewModel {

    TaskRepository taskRepository;

    public AddActivityViewModel(@NonNull Application application) {
        super(application);
        taskRepository = new TaskRepository(application);
    }

    public void insertTask(TaskTable taskTable) {
        taskRepository.insertTask(taskTable);
    }



}
