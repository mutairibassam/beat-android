package com.datum.android.pagingapp.network.api;

import androidx.paging.PagedList;

import com.datum.android.pagingapp.data.Post;
import com.datum.android.pagingapp.network.Routes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET(Routes.END_POINT)
    Call<List<Post>> fetchPosts();

    @GET(Routes.END_POINT)
    Call<PagedList<Post>> fetchPostsPaging();


}
