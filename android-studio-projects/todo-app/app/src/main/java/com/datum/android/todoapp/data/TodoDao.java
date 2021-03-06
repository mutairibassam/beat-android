package com.datum.android.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTask(TodoTable todoTable);

    @Query("SELECT * FROM list_table ORDER BY id ASC")
    LiveData<List<TodoTable>> getAllTasks();

}
