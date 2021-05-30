package com.datum.android.pagingapp.pagingimplementation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.pagingapp.R;
import com.datum.android.pagingapp.data.Issue;
import com.datum.android.pagingapp.data.Post;

public class PostsListPagedAdapter extends PagedListAdapter<Post, PostsListPagedAdapter.PostListViewHolder> {

    private static final String TAG = PostsListPagedAdapter.class.getSimpleName();


    public PostsListPagedAdapter() {
        super(diffCallback);
    }

    private static DiffUtil.ItemCallback<Post> diffCallback = new DiffUtil.ItemCallback<Post>() {
        @Override
        public boolean areItemsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Post oldItem, @NonNull Post newItem) {
            return oldItem.equals(newItem);
        }
    };

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.issue_row_item, parent, false);
        return new PostListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder holder, int position) {
        Post post = getItem(position);
        if(post != null) {
            Log.d(TAG, "onBindViewHolder: " + post.getTitle());
            holder.title.setText(post.getTitle());
        }

    }

    class PostListViewHolder extends RecyclerView.ViewHolder {

        TextView id, title;

        public PostListViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.posts);
        }
    }
}
