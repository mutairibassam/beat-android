package com.datum.android.pagingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.datum.android.pagingapp.adapter.IssueAdapter;
import com.datum.android.pagingapp.adapter.PostAdapter;
import com.datum.android.pagingapp.data.Issue;
import com.datum.android.pagingapp.data.Post;
import com.datum.android.pagingapp.pagingimplementation.MainActivityViewModelPaging;
import com.datum.android.pagingapp.pagingimplementation.PostsListPagedAdapter;
import com.datum.android.pagingapp.viewmodel.MainActivityViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    RecyclerView recyclerView;
    IssueAdapter issueAdapter;
    List<Issue> reporterList = Issue.getNumbers();

    PostAdapter postAdapter;
    List<Post> postList;

//    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.issue_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));

        /**
         *      Fetch Dummy Data with view model
         */

//        issueAdapter = new IssueAdapter(reporterList);
//        recyclerView.setAdapter(issueAdapter);


        /**
         *      Fetch API without view model
         *
         */

//        Service service = Client.getRetrofit().create(Service.class);
//        Call<List<PostData>> call = service.fetchPosts();
//        call.enqueue(new Callback<List<PostData>>() {
//            @Override
//            public void onResponse(Call<List<PostData>> call, Response<List<PostData>> response) {
//                Log.d(TAG, "onResponse: " + response.body());
//                postList = response.body();
//                recyclerView = findViewById(R.id.issue_rv);
//                recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
//                postAdapter = new PostAdapter(postList);
//                recyclerView.setAdapter(postAdapter);
//            }
//
//            @Override
//            public void onFailure(Call<List<PostData>> call, Throwable t) {
//
//            }
//        });


        /**
         *      Fetch API with view model
         */

//        postAdapter = new PostAdapter(postList);
//        recyclerView.setAdapter(postAdapter);
//        viewModel = new MainActivityViewModel(getApplication());
//        viewModel.getPost();
//        viewModel.getPosts().observe(this, postData -> {
//            if(postData != null) {
//                postList = postData;
//                postAdapter.setPostList(postList);
//            }
//        });


        /**
         *      Fetch API with view model + pagination
         */

        PostsListPagedAdapter postsListPagedAdapter = new PostsListPagedAdapter();
        MainActivityViewModelPaging viewModelPaging = new ViewModelProvider(this).get(MainActivityViewModelPaging.class);
        viewModelPaging.getPostsPagedList().observe(this, new Observer<PagedList<Post>>() {
            @Override
            public void onChanged(PagedList<Post> posts) {
                postsListPagedAdapter.submitList(posts);
            }
        });
        recyclerView.setAdapter(postsListPagedAdapter);

    }

}