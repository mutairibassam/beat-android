package com.datum.android.recyclerviewapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.recyclerviewapp.R;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    // Skeleton Format for RecyclerView

    // bring the data here

    Context mContext;
    String[] dummyData;
    int[] img;


    public ListAdapter(Context context, String[] s, int[] i) {
        mContext = context;
        dummyData = s;
        img = i;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        View myView = layoutInflater.inflate(R.layout.each_row, parent, false);
        return new MyViewHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


    }

    @Override
    public int getItemCount() {
        return dummyData.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

        }
    }
}
