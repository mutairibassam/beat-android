package com.datum.android.recyclerviewapp.oldlayout.networking.generators;

import android.util.Log;

import com.datum.android.recyclerviewapp.oldlayout.networking.Routes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataServiceGenerator {

    private static final String TAG = "DataServiceGenerator";

    private static Retrofit retrofit = null;

    public static Retrofit getRetrofit() {

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(Routes.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Log.d(TAG, "getRetrofit() method " + retrofit);
        }

        return retrofit;

    }

}
