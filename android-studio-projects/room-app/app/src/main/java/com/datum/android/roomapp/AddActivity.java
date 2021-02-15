package com.datum.android.roomapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {


    EditText word, meaning, type;

    private AddNewWordViewModel mViewModel;

    public static final String EXTRA_ID = "com.datum.android.roomapp.extra_id";
    private boolean isEditMode = false;
    private int id;

    public static final String EXTRA_WORD = "com.datum.android.roomapp.word";
    public static final String EXTRA_MEANING = "com.datum.android.roomapp.meaning";
    public static final String EXTRA_TYPE = "com.datum.android.roomapp.type";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        word = findViewById(R.id.et_word_name);
        meaning = findViewById(R.id.et_word_meaning);
        type = findViewById(R.id.et_word_type);

        mViewModel = ViewModelProviders.of(this).get(AddNewWordViewModel.class);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24);

        Intent intent = getIntent();
        if(intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Word");
            isEditMode = true;
            id = intent.getIntExtra(EXTRA_ID, -2);
            word.setText(intent.getStringExtra(EXTRA_WORD));
            meaning.setText(intent.getStringExtra(EXTRA_MEANING));
            type.setText(intent.getStringExtra(EXTRA_TYPE));

        } else {
            setTitle("Add New Word");
            isEditMode = false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveWord();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }

    }

    private void saveWord() {
        String wordText = word.getText().toString().trim();
        String meaningText = meaning.getText().toString().trim();
        String typeText = type.getText().toString().trim();

        if(wordText.isEmpty() || meaningText.isEmpty() || typeText.isEmpty()) {
            Toast.makeText(this, "Please fill all the details", Toast.LENGTH_SHORT).show();
            return;
        }

        Words wordObject = new Words(wordText, meaningText, typeText);
        if(isEditMode) {
            wordObject.setId(id);
            mViewModel.update(wordObject);
        } else {
            mViewModel.insert(wordObject);
        }

        finish();

    }
}