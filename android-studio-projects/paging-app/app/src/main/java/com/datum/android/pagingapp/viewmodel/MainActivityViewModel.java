package com.datum.android.pagingapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.datum.android.pagingapp.data.Issue;
import com.datum.android.pagingapp.data.Post;
import com.datum.android.pagingapp.network.api.Service;
import com.datum.android.pagingapp.network.generator.Client;
import com.datum.android.pagingapp.repository.IssueRepository;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends AndroidViewModel {

    IssueRepository issueRepository;
    private MutableLiveData<List<Post>> postList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        issueRepository = new IssueRepository(application);
        postList = new MutableLiveData<>();
    }

//    public LiveData<List<Issue>> getAllTasks() {
//        return issueRepository.getAllIssues();
//    }

    public MutableLiveData<List<Post>> getPosts() {
        return postList;
    }

    public void getPost() {
        Service service = Client.getRetrofit().create(Service.class);
        Call<List<Post>> call = service.fetchPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                postList.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                postList.postValue(null);
            }
        });
    }




}
