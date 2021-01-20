package com.datum.android.recyclerviewapp.newlayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.recyclerviewapp.R;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyNewViewHolder> {

    List<Model> myList;

    public MyAdapter(List<Model> myList) {
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyNewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.new_row,parent, false);
        return new MyNewViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull MyNewViewHolder holder, int pos) {

        holder.name.setText(myList.get(pos).getName());
        holder.email.setText(myList.get(pos).getEmail());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public static class MyNewViewHolder extends RecyclerView.ViewHolder{

        TextView name, email;

        public MyNewViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);

        }


    }
}
