package com.datum.android.pagingapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.datum.android.pagingapp.data.Issue;
import com.datum.android.pagingapp.dao.IssueDao;

@Database(entities = {Issue.class}, version = 2, exportSchema = false)
public abstract class IssueDatabase extends RoomDatabase {

    public abstract IssueDao issueDao();

    //Create the WordRoomDatabase as a singleton to prevent having multiple instances of the database opened
    //at the same time, which would be a bad thing
    private static IssueDatabase INSTANCE;
    public static final String DATABASE_NAME = "ISSUE_DATABASE";

    // singlton: to make sure there is no more than one copy
    // @synchronized one thread only can deal with this database
    public static synchronized IssueDatabase getInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    IssueDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return INSTANCE;
    }

}
