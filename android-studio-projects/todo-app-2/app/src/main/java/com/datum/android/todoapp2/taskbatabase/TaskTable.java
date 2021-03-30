package com.datum.android.todoapp2.taskbatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class TaskTable {

    public TaskTable(String task) {
        this.task = task;
    }

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "task")
    String task;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }
}
