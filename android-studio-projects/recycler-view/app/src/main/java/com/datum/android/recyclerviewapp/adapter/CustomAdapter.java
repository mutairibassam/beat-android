package com.datum.android.recyclerviewapp.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.recyclerviewapp.R;
import com.datum.android.recyclerviewapp.model.MyCustomAPI;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    Context mContext;

    // Mydata
    List<MyCustomAPI> myList;

    public CustomAdapter(Context mContext, List<MyCustomAPI> myList) {
        this.mContext = mContext;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_api_row, parent,false);


//        Context context = parent.getContext();
//
//        int layout = R.layout.custom_api_row;
//
//        LayoutInflater inflater = LayoutInflater.from(context);
//
//        View view1 = inflater.inflate(layout, parent, false);


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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, email;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);

        }
    }



}
