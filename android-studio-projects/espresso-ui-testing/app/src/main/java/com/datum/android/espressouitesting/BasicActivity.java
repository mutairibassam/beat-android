package com.datum.android.espressouitesting;

import android.content.Context;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BasicActivity extends AppCompatActivity {

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("UI Espresso");
        setSupportActionBar(toolbar);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.save:
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),
                        "This item has been saved", Snackbar.LENGTH_LONG)
                        .setAction("Undo", view ->
                                Toast.makeText(getApplicationContext(),
                                        "Item is not saved anymore",
                                        Toast.LENGTH_LONG).show()).show();
                return true;
            case R.id.delete:
                Snackbar.make(getWindow().getDecorView().findViewById(android.R.id.content),
                        "This item has been deleted", Snackbar.LENGTH_LONG)
                        .setAction("Undo", view ->
                                Toast.makeText(getApplicationContext(),
                                        "Item is not deleted anymore",
                                        Toast.LENGTH_LONG).show()).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}