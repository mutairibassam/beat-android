package com.datum.android.todoapp2.taskbatabase;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertTask(TaskTable taskTable);

    @Query("SELECT * FROM task_table")
    LiveData<List<TaskTable>> getAllTasks();
}
