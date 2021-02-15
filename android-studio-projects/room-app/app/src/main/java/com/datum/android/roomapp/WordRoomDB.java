package com.datum.android.roomapp;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Words.class, version = 2)
public abstract class WordRoomDB extends RoomDatabase {

    // we wanna copy of the class
    // since we need only to create one copy of this DB "Singliton"
    // and to use the same instance
    private static WordRoomDB instance;

    public abstract WordsDao wordsDao();

    // singlton: to make sure there is no more than one copy
    // @synchronized one thread only can deal with this database
    public static synchronized WordRoomDB getInstance(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    WordRoomDB.class,
                    "word-database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {

        // called the first time when the user run the application
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }

        // called everytime the user open the application
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void, Void, Void> {

        private WordsDao mWordDao;
        PopulateDataAsyncTask(WordRoomDB db) {
            mWordDao = db.wordsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
//            mWordDao.insert(new Words("Book", "كتاب", "Noun"));
            return null;
        }
    }
}
