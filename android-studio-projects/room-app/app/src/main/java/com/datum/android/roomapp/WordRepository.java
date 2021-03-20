package com.datum.android.roomapp;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import androidx.work.WorkRequest;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import java.util.List;

public class WordRepository {

    private WordsDao mWordsDao;
    private LiveData<List<Words>> getAllWords;

//    public WordRepository(WordsDao mWordsDao, LiveData<List<Words>> getAllWords) {
//        this.mWordsDao = mWordsDao;
//        this.getAllWords = getAllWords;
//    }

    public WordRepository(Application application) {
        WordRoomDB db = WordRoomDB.getInstance(application);
        mWordsDao = db.wordsDao();
        getAllWords = mWordsDao.getAllWords();
    }


    // operation: insert delete update getallwords delete all words
    // it should be in different thread



//    public void insert(Words word) {
//        new InsertAsyncTask(mWordsDao).execute(word);
//    }

//    private static class InsertAsyncTask extends AsyncTask<Words, Void, Void> {
//        private WordsDao mWordsDao;
//        public InsertAsyncTask(WordsDao wordsDao)
//        {
//            mWordsDao = wordsDao;
//        }
//        @Override
//        protected Void doInBackground(Words... words) {
//            mWordsDao.insert(words[0]);
//            return null;
//        }
//    }

    public void delete(Words word) {
        new DeleteAsyncTask(mWordsDao).execute(word);
    }

    public void update(Words word) {
        new UpdateAsyncTask(mWordsDao).execute(word);

    }

    public LiveData<List<Words>> getAllWords() {
        return getAllWords;
    }

    public void deleteAllWords() {
        new DeleteAsyncTask(mWordsDao).execute();
    }

    private static class DeleteAsyncTask extends AsyncTask<Words, Void, Void>{
        private WordsDao mWordsDao;
        public DeleteAsyncTask(WordsDao wordsDao)
        {
            mWordsDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.delete(words[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Words, Void, Void>{
        private WordsDao mWordsDao;
        public UpdateAsyncTask(WordsDao wordsDao)
        {
            mWordsDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.update(words[0]);
            return null;
        }
    }
    private static class DeleteAllWordsAsyncTask extends AsyncTask<Void, Void, Void>{
        private WordsDao mWordsDao;
        public DeleteAllWordsAsyncTask(WordsDao wordsDao)
        {
            mWordsDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllWords();
            return null;
        }
    }




}
