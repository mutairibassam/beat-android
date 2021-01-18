package com.datum.android.recyclerviewapp.oldlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;

import com.datum.android.recyclerviewapp.R;
import com.datum.android.recyclerviewapp.oldlayout.adapter.CustomAdapter;
import com.datum.android.recyclerviewapp.oldlayout.model.MyCustomTable;
import com.datum.android.recyclerviewapp.oldlayout.networking.api.Service;
import com.datum.android.recyclerviewapp.oldlayout.networking.generators.DataServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OldMainActivity extends AppCompatActivity {

    private static final String TAG = OldMainActivity.class.getSimpleName();


    CustomAdapter customAdapter;
    List<MyCustomTable> myCustomList;

    RecyclerView mRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);

        fetchMyCustomAPI();

    }



    private void fetchMyCustomAPI() {

        Service service = DataServiceGenerator.getRetrofit().create(Service.class);

        Call<List<MyCustomTable>> call = service.fetchMyCustomAPI();

        call.enqueue(new Callback<List<MyCustomTable>>() {
            @Override
            public void onResponse(Call<List<MyCustomTable>> call, Response<List<MyCustomTable>> response) {

//                myCustomList = response.body();
                myCustomList = new MyCustomTable().getData();


                // Use only one
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
//                mRecyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));
//                mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,LinearLayoutManager.VERTICAL));


//                StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL);
//                mRecyclerView.setLayoutManager(staggeredGridLayoutManager);


                // create a new custom Adapter
                customAdapter = new CustomAdapter(getApplicationContext(), myCustomList);

                // take the data and let the recyclerview present it
                mRecyclerView.setAdapter(customAdapter);


            }

            @Override
            public void onFailure(Call<List<MyCustomTable>> call, Throwable t) {

            }
        });
    }
}