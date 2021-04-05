package com.datum.android.pagingwithcache;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.datum.android.pagingwithcache.data.Post;
import com.datum.android.pagingwithcache.pagingimplementation.MainActivityViewModelPaging;
import com.datum.android.pagingwithcache.pagingimplementation.PostsListPagedAdapter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // call api
        // attach rv adapter


        // call api in view model
        // attach rv adapter livedata observer


        // adapter ListPagedAdapter
                // --> data source
                // --> data source factory
                // --> adapter
                // --> view model

        RecyclerView recyclerView = findViewById(R.id.rv_posts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        PostsListPagedAdapter postsListPagedAdapter = new PostsListPagedAdapter();
        MainActivityViewModelPaging viewModelPaging = new ViewModelProvider(this).get(MainActivityViewModelPaging.class);
        viewModelPaging.getPostsPagedList().observe(this, posts -> {
                postsListPagedAdapter.submitList(posts);
        });
        recyclerView.setAdapter(postsListPagedAdapter);

    }
}