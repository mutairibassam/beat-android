package com.datum.android.recyclerviewapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.recyclerviewapp.R;
import com.datum.android.recyclerviewapp.model.MyCustomModel;

import java.util.List;

public class MyCustomAdapter extends RecyclerView.Adapter<MyCustomAdapter.MyViewHolder> {

    Context mContext;
    List<MyCustomModel> myList;


    public MyCustomAdapter(Context mContext, List<MyCustomModel> myList) {
        this.mContext = mContext;
        this.myList = myList;
    }

    @NonNull
    @Override
    public MyCustomAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int listItem = R.layout.each_row;
        LayoutInflater inflater = LayoutInflater.from(context);

        View myView = inflater.inflate(listItem, parent, false);

//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.movie_card, viewGroup, false);

        return new MyViewHolder(myView);

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mTitle.setText(myList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return myList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.title_id);
        }
    }
}
