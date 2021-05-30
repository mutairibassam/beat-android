package com.datum.android.pagingapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.datum.android.pagingapp.data.Issue;
import com.datum.android.pagingapp.dao.IssueDao;
import com.datum.android.pagingapp.database.IssueDatabase;

import java.util.List;

public class IssueRepository {
    IssueDao issueDao;
    IssueDatabase database;

    public IssueRepository(Application application) {
        database = IssueDatabase.getInstance(application);
        issueDao = database.issueDao();
    }

//    public LiveData<List<Issue>> getAllIssues() {
//        return issueDao.getAllIssues();
//    }
}
