package com.datum.android.sharedpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.datum.android.sharedpreferences.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public static final String SHARED_PREFERENCES_NAME = "CONTACT";
    public static final String KEY_NAME = "NAME";
    public static final String KEY_EMAIL = "EMAIL";

    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    private SharedPreferences.OnSharedPreferenceChangeListener listner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.saveButton.setOnClickListener(View -> {
            String name = binding.etName.getText().toString();
            String email = binding.etEmail.getText().toString();

            sharedPref = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);
            editor = sharedPref.edit();

            editor.putString(KEY_NAME, name);
            editor.putString(KEY_EMAIL, email);
            editor.apply();

            binding.etName.setText("");
            binding.etEmail.setText("");

        });

        binding.restoreButton.setOnClickListener(View -> {
            sharedPref = getSharedPreferences(SHARED_PREFERENCES_NAME, MODE_PRIVATE);

            String name = sharedPref.getString(KEY_NAME, "Empty");
            String email = sharedPref.getString(KEY_EMAIL, "Empty");

            binding.tvData.setText(name + " " + email);



            /**
             * Limitation in the shared-preferences to fetch more than name,
             * since it will overwrite the previous name
             *
             * 1- increment the index.
             * 2- use split method.
             *
             */

//            Map<String, ?> all = sharedPref.getAll();
//            ArrayList<String> list = new ArrayList<>();
//            for(Map.Entry<String, ?> entry : all.entrySet()) {
//                list.add(entry.getValue().toString());
//            }
//
//            binding.tvData.setText(list.toString());


        });

        binding.clearButton.setOnClickListener(View -> {
            editor = sharedPref.edit();
//            editor.remove(KEY_NAME);      // remove specific key
            editor.clear();               // remove all keys
            editor.apply();              // commit the changes to be implemented
        });








        binding.internalStorageButton.setOnClickListener(View -> {
            startActivity(new Intent(MainActivity.this, InternalStorage.class));
        });

        binding.externalButton.setOnClickListener(View -> {
            startActivity(new Intent(MainActivity.this, ExternalStorage.class));
        });

    }
}