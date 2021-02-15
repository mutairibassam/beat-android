package com.datum.android.roomapp;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepository {

    private WordsDao mWordsDao;
    private LiveData<List<Words>> getAllWords;

    public WordRepository(Application application) {
        WordRoomDB db = WordRoomDB.getInstance(application);
        mWordsDao = db.wordsDao();
        getAllWords = mWordsDao.getAllWords();
    }

    // operation: insert delete update getallwords delete all words
    // it should be in different thread

    public void insert(Words word) {
        new InsertAsyncTask(mWordsDao).execute(word);
    }

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

    private static class InsertAsyncTask extends AsyncTask<Words, Void, Void> {
        private WordsDao mWordsDao;
        public InsertAsyncTask(WordsDao wordsDao)
        {
            mWordsDao = wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.insert(words[0]);
            return null;
        }
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
