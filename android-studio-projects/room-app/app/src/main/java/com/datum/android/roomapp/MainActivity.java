package com.datum.android.roomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;

    private RecyclerView mRecyclerView;
    private WordAdapter mWordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton floatingActionButton = findViewById(R.id.floating_button);
        floatingActionButton.setOnClickListener(View -> {
            // go to add activity
            Intent intent = new Intent(this, AddActivity.class);
            startActivity(intent);
        });

        mRecyclerView = findViewById(R.id.word_recyclerView_id);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setHasFixedSize(true);

        mWordAdapter = new WordAdapter();
        mRecyclerView.setAdapter(mWordAdapter);

        mWordViewModel = ViewModelProviders.of(this).get(WordViewModel.class);

        // onChange callback will be called if there is any changes
        mWordViewModel.getAllWords().observe(this, words -> {
            // Update UI
            // RecyclerView

            // bring the new words that come from Room
            mWordAdapter.setWords(words);


        });

        mWordAdapter.OnItemClickListener(new WordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Words word) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                intent.putExtra(AddActivity.EXTRA_WORD, word.getWordName());
                intent.putExtra(AddActivity.EXTRA_MEANING, word.getWordMeaning());
                intent.putExtra(AddActivity.EXTRA_TYPE, word.getWordType());

                intent.putExtra(AddActivity.EXTRA_ID, word.getId());

                startActivity(intent);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // delete from word list
                int pos = viewHolder.getAdapterPosition();

                // we need to specify the position id from the adapter. so we created getWordAt();
                mWordViewModel.delete(mWordAdapter.getWordAt(pos));
            }
        }).attachToRecyclerView(mRecyclerView);
    }
}