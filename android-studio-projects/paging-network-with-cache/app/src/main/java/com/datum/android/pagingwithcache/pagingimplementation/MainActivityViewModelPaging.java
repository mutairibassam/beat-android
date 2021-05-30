package com.datum.android.pagingwithcache.pagingimplementation;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.datum.android.pagingwithcache.data.Post;

public class MainActivityViewModelPaging extends ViewModel {

    private LiveData<PagedList<Post>> postsPagedList;
    private LiveData<PageKeyedDataSource<Integer, Post>> liveDataSource;

    public MainActivityViewModelPaging() {

        PostDataSourceFactory postDataSourceFactory = new PostDataSourceFactory();
        liveDataSource = postDataSourceFactory.getPostsLiveData();

        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(true)
                .setPageSize(3)
                .setMaxSize(9)
                .setInitialLoadSizeHint(3)
                .build();

        postsPagedList = new LivePagedListBuilder(postDataSourceFactory, config).build();
    }

    public LiveData<PagedList<Post>> getPostsPagedList() {
        return postsPagedList;
    }
}
