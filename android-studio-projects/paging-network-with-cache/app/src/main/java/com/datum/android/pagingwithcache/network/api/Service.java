package com.datum.android.pagingwithcache.network.api;

import androidx.paging.PagedList;

import com.datum.android.pagingwithcache.data.Post;
import com.datum.android.pagingwithcache.network.Routes;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET(Routes.END_POINT)
    Call<PagedList<Post>> fetchPostsPaging();
}
