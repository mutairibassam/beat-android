package com.datum.android.recyclerviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.datum.android.recyclerviewapp.adapter.AlbumsAdapter;
import com.datum.android.recyclerviewapp.adapter.ListAdapter;
import com.datum.android.recyclerviewapp.adapter.MyCustomAdapter;
import com.datum.android.recyclerviewapp.adapter.PostsAdapter;
import com.datum.android.recyclerviewapp.model.AlbumsModel;
import com.datum.android.recyclerviewapp.model.MyCustomModel;
import com.datum.android.recyclerviewapp.model.PostsModel;
import com.datum.android.recyclerviewapp.networking.api.Service;
import com.datum.android.recyclerviewapp.networking.generators.DataServiceGenerator;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView mRecyclerView;

    PostsAdapter mPostsAdapter;
    List<PostsModel> mPostsList;

    AlbumsAdapter mAlbumsAdapter;
    List<AlbumsModel> mAlbumsList;

    MyCustomAdapter myCustomAdapter;
    List<MyCustomModel> myCustomModelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchMyCustomAPI();

//        fetchPosts();
//        fetchAlbums();

    }

    private void fetchMyCustomAPI() {
        Service service = DataServiceGenerator.getRetrofit().create(Service.class);
        Call<List<MyCustomModel>> call = service.fetchCustomAPI();


        call.enqueue(new Callback<List<MyCustomModel>>() {
            @Override
            public void onResponse(Call<List<MyCustomModel>> call, Response<List<MyCustomModel>> response) {
                Log.d(TAG, "onResponse: " + response.body());

                myCustomModelList = response.body();
                mRecyclerView = findViewById(R.id.recyclerview);
                myCustomAdapter = new MyCustomAdapter(getApplicationContext(), myCustomModelList);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                mRecyclerView.setAdapter(myCustomAdapter);

            }

            @Override
            public void onFailure(Call<List<MyCustomModel>> call, Throwable t) {
                Log.d(TAG, "onFailure: " + t.getMessage());
                if (t instanceof IOException) {
                    Toast.makeText(MainActivity.this, "this is an actual network failure", Toast.LENGTH_SHORT).show();
                    // logging probably not necessary
                }
                else {
                    Toast.makeText(MainActivity.this, "conversion issue! big problems :(", Toast.LENGTH_SHORT).show();
                    // todo log to some central bug tracking service
                }

            }
        });
    }

//    private void fetchAlbums() {
//        Service service = DataServiceGenerator.getRetrofit().create(Service.class);
//        Call<List<AlbumsModel>> call = service.fetchAlbum();
//
//        call.enqueue(new Callback<List<AlbumsModel>>() {
//            @Override
//            public void onResponse(Call<List<AlbumsModel>> call, Response<List<AlbumsModel>> response) {
//                Log.d(TAG, "onResponse: " + response.message());
//
//                mAlbumsList = response.body();
//                mRecyclerView = findViewById(R.id.recyclerview);
//                mAlbumsAdapter = new AlbumsAdapter(getApplicationContext(), mAlbumsList);
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                mRecyclerView.setAdapter(mAlbumsAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<AlbumsModel>> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
//
//            }
//        });
//    }

//    private void fetchPosts() {
//
//        Service service = DataServiceGenerator.getRetrofit().create(Service.class);
//
//        Call<List<PostsModel>> call = service.fetchPosts();
//
//        call.enqueue(new Callback<List<PostsModel>>() {
//            @Override
//            public void onResponse(Call<List<PostsModel>> call, Response<List<PostsModel>> response) {
//
//                Log.d(TAG, "onResponse: " + response.message());
//                mPostsList = response.body();
//
//                mRecyclerView = findViewById(R.id.recyclerview);
//
//                mPostsAdapter = new PostsAdapter(getApplicationContext(), mPostsList);
//
//                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//
//                mRecyclerView.setAdapter(mPostsAdapter);
//
//
//            }
//
//            @Override
//            public void onFailure(Call<List<PostsModel>> call, Throwable t) {
//                Log.d(TAG, "onFailure: " + t.getMessage());
//
//            }
//        });
//    }
}