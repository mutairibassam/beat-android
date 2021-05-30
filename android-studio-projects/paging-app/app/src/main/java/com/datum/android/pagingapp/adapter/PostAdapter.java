package com.datum.android.pagingapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.pagingapp.R;
import com.datum.android.pagingapp.data.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    List<Post> postList;

    public PostAdapter(List<Post> postList) {
        this.postList = postList;
    }

    public void setPostList(List<Post> postList) {
        this.postList = postList;
        notifyDataSetChanged();;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(postList.get(position));
    }

    @Override
    public int getItemCount() {
        if(postList != null) {
            return postList.size();
        } else return 0;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, title, body;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.post_id);
            title = itemView.findViewById(R.id.post_title);
            body = itemView.findViewById(R.id.post_body);

        }

        void bind(Post postData) {
            id.setText(postData.getId() + "");
            title.setText(postData.getTitle());
//            body.setText(postData.getBody());
        }
    }
}
