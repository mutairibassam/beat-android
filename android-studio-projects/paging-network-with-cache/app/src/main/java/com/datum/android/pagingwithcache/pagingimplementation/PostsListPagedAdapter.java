package com.datum.android.pagingwithcache.pagingimplementation;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.pagingwithcache.R;
import com.datum.android.pagingwithcache.data.Post;

public class PostsListPagedAdapter extends PagedListAdapter<Post, PostsListPagedAdapter.PostListViewModel> {

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
    public PostListViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row_item,parent,false);
        return new PostListViewModel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewModel holder, int position) {
        Post post = getItem(position);
        if(post != null) {
            Log.d(TAG, "onBindViewHolder: " + post.getTitle());
            holder.title.setText(post.getTitle());
        }
    }

    public class PostListViewModel extends RecyclerView.ViewHolder {

        TextView title;

        public PostListViewModel(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.post_title);
        }
    }
}
