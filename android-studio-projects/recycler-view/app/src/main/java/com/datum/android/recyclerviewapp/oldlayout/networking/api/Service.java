package com.datum.android.recyclerviewapp.oldlayout.networking.api;

import com.datum.android.recyclerviewapp.oldlayout.model.MyCustomTable;
import com.datum.android.recyclerviewapp.oldlayout.networking.Routes;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Service {

    @GET(Routes.END_POINT)
    Call<List<MyCustomTable>> fetchMyCustomAPI();

}
