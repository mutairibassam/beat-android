package com.datum.android.todoapp.data;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface TodoDao {

    // @OnConflictStrategy.IGNORE will not insert if the same id already stored
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addTask(TodoTable todoTable);


    // @LiveData we want to monitor the changes, and to update the UI in case there is any changes
    @Query("SELECT * FROM list_table ORDER BY id ASC")
    LiveData<List<TodoTable>> getAllTasks();

}
