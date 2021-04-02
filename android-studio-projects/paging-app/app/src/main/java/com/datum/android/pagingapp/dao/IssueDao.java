package com.datum.android.pagingapp.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Query;

import com.datum.android.pagingapp.data.Issue;

import java.lang.annotation.Target;
import java.util.List;

@Dao
public interface IssueDao {

    @Query("SELECT * FROM issue_table")
    LiveData<List<Issue>> getAllIssues();

}
