package com.datum.android.pagingapp.pagingimplementation;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.datum.android.pagingapp.dao.IssueDao;
import com.datum.android.pagingapp.data.Issue;
import com.datum.android.pagingapp.data.Post;
import com.datum.android.pagingapp.network.api.Service;
import com.datum.android.pagingapp.network.generator.Client;
import com.datum.android.pagingapp.repository.IssueRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModelPaging extends ViewModel {

    private LiveData<PagedList<Post>> postsPagedList;
    private LiveData<PageKeyedDataSource<Integer, Post>> liveDataSource;

    public MainActivityViewModelPaging() {
        PostDataSourceFactory postDataSourceFactory = new PostDataSourceFactory();
        liveDataSource = postDataSourceFactory.getPostsLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(5)
                .build();

        postsPagedList = new LivePagedListBuilder(postDataSourceFactory, config).build();
    }

    public LiveData<PagedList<Post>> getPostsPagedList() {
        return postsPagedList;
    }

//    private IssueRepository issueRepository;
//    public final LiveData<PagedList<Issue>> issueList;
//
//    public MainActivityViewModelPaging(IssueRepository issueRepository) {
//        this.issueRepository = issueRepository;
//        issueList = new LivePagedListBuilder<>(issueRepository.getIssues(), 10).build();
//    }
}
