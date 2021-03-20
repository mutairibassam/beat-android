package com.datum.android.recyclerviewapp.newlayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.TextView;

import com.datum.android.recyclerviewapp.R;

import java.util.List;

public class NewMainActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    List<Model> myList = new Model().getData();

    MyAdapter mycustomAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_main);

        recyclerView = findViewById(R.id.recyclerview2);

        mycustomAdapter = new MyAdapter(myList, NewMainActivity.this);


        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        recyclerView.setAdapter(mycustomAdapter);



    }
}