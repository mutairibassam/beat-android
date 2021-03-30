package com.datum.android.todoapp2.taskbatabase;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TaskTable.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {

    public abstract TaskDao taskDao();

    //  Create the WordRoomDatabase as a singleton to prevent having multiple instances of the database opened
    //  at the same time, which would be a bad thing
    private static TaskDatabase INSTANCE;
    public static final String DATABASE_NAME = "TASK_DATABASE";

    //  singlton: to make sure there is no more than one copy
    //  @synchronized one thread only can deal with this database
    public static synchronized TaskDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }
}
