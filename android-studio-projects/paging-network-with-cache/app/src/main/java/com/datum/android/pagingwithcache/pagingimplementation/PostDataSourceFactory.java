package com.datum.android.pagingwithcache.pagingimplementation;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.datum.android.pagingwithcache.data.Post;

public class PostDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Post>> postsLiveData = new MutableLiveData<>();

    @NonNull
    @Override
    public DataSource create() {
        PostDataSource postDataSource = new PostDataSource();
        postsLiveData.postValue(postDataSource);
        return postDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Post>> getPostsLiveData() {
        return postsLiveData;
    }


}
