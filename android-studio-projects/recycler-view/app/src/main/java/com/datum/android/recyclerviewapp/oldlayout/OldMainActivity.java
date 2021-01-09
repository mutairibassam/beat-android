package com.datum.android.recyclerviewapp.oldlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.datum.android.recyclerviewapp.R;
import com.datum.android.recyclerviewapp.oldlayout.adapter.CustomAdapter;
import com.datum.android.recyclerviewapp.oldlayout.model.MyCustomAPI;
import com.datum.android.recyclerviewapp.oldlayout.networking.api.Service;
import com.datum.android.recyclerviewapp.oldlayout.networking.generators.DataServiceGenerator;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OldMainActivity extends AppCompatActivity {

    private static final String TAG = OldMainActivity.class.getSimpleName();

    RecyclerView mRecyclerView;

    CustomAdapter customAdapter;
    List<MyCustomAPI> myCustomAPI_List;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview);

        fetchMyCustomAPI();

    }

    private void fetchMyCustomAPI() {

        Service service = DataServiceGenerator.getRetrofit().create(Service.class);

        Call<List<MyCustomAPI>> call = service.fetchMyCustomAPI();

        call.enqueue(new Callback<List<MyCustomAPI>>() {
            @Override
            public void onResponse(Call<List<MyCustomAPI>> call, Response<List<MyCustomAPI>> response) {

                myCustomAPI_List = response.body();

                customAdapter = new CustomAdapter(getApplicationContext(), myCustomAPI_List);

                mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

                mRecyclerView.setAdapter(customAdapter);
            }

            @Override
            public void onFailure(Call<List<MyCustomAPI>> call, Throwable t) {

            }
        });
    }
}