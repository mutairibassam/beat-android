package com.datum.android.recyclerviewapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.recyclerviewapp.R;
import com.datum.android.recyclerviewapp.model.PostsModel;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.MyViewHolder> {

    Context mContext;
    List<PostsModel> mPostsList;

    public PostsAdapter(Context mContext, List<PostsModel> mPostsList) {
        this.mContext = mContext;
        this.mPostsList = mPostsList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        Context context = parent.getContext();
        int layoutListItem = R.layout.each_row;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(layoutListItem, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.mTitle.setText(mPostsList.get(position).getTitle());

    }

    @Override
    public int getItemCount() {
        if(mPostsList == null) {
            return 0;
        }
        return mPostsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mTitle;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mTitle = itemView.findViewById(R.id.title_id);
        }
    }
}
