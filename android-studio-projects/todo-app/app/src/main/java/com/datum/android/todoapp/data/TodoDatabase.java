package com.datum.android.todoapp.data;


import android.content.Context;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TodoTable.class}, version = 1, exportSchema = false)
public abstract class TodoDatabase extends RoomDatabase {

    public abstract TodoDao todoDao();

    //Create the WordRoomDatabase as a singleton to prevent having multiple instances of the database opened
    //at the same time, which would be a bad thing
    private static TodoDatabase INSTANCE;

    // singlton: to make sure there is no more than one copy
    // @synchronized one thread only can deal with this database
    public static synchronized TodoDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TodoDatabase.class, "todo_database")
                    .build();
        }

        return INSTANCE;
    }

}
