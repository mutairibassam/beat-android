package com.datum.android.pagingwithcache.pagingimplementation;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.datum.android.pagingwithcache.data.Post;
import com.datum.android.pagingwithcache.network.api.Service;
import com.datum.android.pagingwithcache.network.generator.Client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostDataSource extends PageKeyedDataSource<Integer, Post> {

    private static final String TAG = PostDataSource.class.getSimpleName();

    public static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Post> callback) {
        Service service = Client.getRetrofit().create(Service.class);
        Call<PagedList<Post>> call = service.fetchPostsPaging();
        call.enqueue(new Callback<PagedList<Post>>() {
            @Override
            public void onResponse(Call<PagedList<Post>> call, Response<PagedList<Post>> response) {
                if(response.body() != null) {
                    callback.onResult(response.body(), null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<PagedList<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Post> callback) {

    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Post> callback) {
        Service service = Client.getRetrofit().create(Service.class);
        Call<PagedList<Post>> call = service.fetchPostsPaging();
        call.enqueue(new Callback<PagedList<Post>>() {
            @Override
            public void onResponse(Call<PagedList<Post>> call, Response<PagedList<Post>> response) {
                if(response.body() != null) {

                    int key = params.key + 1;
                    callback.onResult(response.body(), key);


                }
            }

            @Override
            public void onFailure(Call<PagedList<Post>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
            }
        });
    }
}
