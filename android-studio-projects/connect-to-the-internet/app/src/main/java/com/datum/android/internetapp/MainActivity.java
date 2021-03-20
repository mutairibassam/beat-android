package com.datum.android.internetapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.datum.android.internetapp.databinding.ActivityMainBinding;

import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    final String BASE_URL = "https://www.google.com/books/v1/volumes?";
    final String QUERY_PARAM = "q";
    final String MAX_RESULTS = "maxResults";
    final String PRINT_TYPE = "printType";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.checkButton.setOnClickListener(View -> {
            oldWay();
//            newWay();
//            theBestWay();
        });

        Uri uri = Uri.parse(BASE_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, "PRIDE+PREJUDICE")
                .appendQueryParameter(MAX_RESULTS, "10")
                .appendQueryParameter(PRINT_TYPE, "books")
                .build();

        try {
            // convert it to url object
            URL request = new URL(uri.toString());

            HttpURLConnection conn = (HttpURLConnection) request.openConnection();


            conn.connect();

            // get the response
            int response = conn.getResponseCode();

            // get the value
            InputStream inputStream = conn.getInputStream();

//            String content = convertInputStreamToString(inputStream, len);
            String content = inputStream.toString();

            JSONObject jsonObject = new JSONObject(content);

            conn.disconnect();
            if(inputStream != null) {
                inputStream.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        // diff lib
        // OkHttp or Volley or Retrofit

    }

    private void theBestWay() {
        Util.checkNetworkInfo(this, new Util.OnConnectionStatusChange() {
            @Override
            public void onChange(boolean type) {
                if(type) Toast.makeText(getApplicationContext(), "Connection Available (the best way)", Toast.LENGTH_SHORT).show();
                else Toast.makeText(getApplicationContext(), "NO Connection (the best way)", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void newWay() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            connectivityManager.registerDefaultNetworkCallback(new ConnectivityManager.NetworkCallback() {
                @Override
                public void onAvailable(@NonNull Network network) {
                    Toast.makeText(getApplicationContext(), "Connection Available (new way)", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onLost(@NonNull Network network) {
                    Toast.makeText(getApplicationContext(), "NO Connection (new way)", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    public void oldWay() {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            Toast.makeText(this, "Connection Available (old way)", Toast.LENGTH_SHORT).show();
        } else {
            // there is no network connection
            Toast.makeText(this, "NO Connection (old way)", Toast.LENGTH_SHORT).show();
        }
    }
}