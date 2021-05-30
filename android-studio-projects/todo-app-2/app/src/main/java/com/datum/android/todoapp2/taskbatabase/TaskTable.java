package com.datum.android.todoapp2.taskbatabase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "task_table")
public class TaskTable {

    @PrimaryKey(autoGenerate = true)
    int id;

    @ColumnInfo(name = "task")
    String task;

    @ColumnInfo(name = "important")
    boolean important;

    public TaskTable(String task, boolean important) {
        this.task = task;
        this.important = important;
    }

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

    public boolean isImportant() {
        return important;
    }

    public void setIsImportant(boolean important) {
        this.important = important;
    }
}
