package com.datum.android.recyclerviewapp.oldlayout.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.recyclerviewapp.R;
import com.datum.android.recyclerviewapp.oldlayout.model.MyCustomTable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    /**
     * @Class CustomAdapter
     *
     */
    Context mContext;               // Required to accept getApplicationContext() as an argument
    List<MyCustomTable> myList;     // already declared


    public CustomAdapter(Context mContext, List<MyCustomTable> myList) {
        this.mContext = mContext;
        this.myList = myList;
    }

    public CustomAdapter(List<MyCustomTable> myList) {
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layout = R.layout.custom_api_row;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToTheParent = false;
        View view = inflater.inflate(layout, parent, shouldAttachToTheParent);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(myList.get(position).getName());
        holder.email.setText(myList.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return myList.size();
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("TAG", "onClick: " + getAdapterPosition());
                }
            });

        }
    }



}
