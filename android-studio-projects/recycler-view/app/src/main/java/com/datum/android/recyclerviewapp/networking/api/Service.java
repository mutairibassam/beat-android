package com.datum.android.recyclerviewapp.networking.api;

import com.datum.android.recyclerviewapp.model.MyCustomAPI;
import com.datum.android.recyclerviewapp.model.PostsModel;
import com.datum.android.recyclerviewapp.networking.Routes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {


//    @GET(Routes.END_POINT)
//    Call<List<PostsModel>> fetchPosts();


    @GET(Routes.END_POINT)
    Call<List<MyCustomAPI>> fetchMyCustomAPI();



}
