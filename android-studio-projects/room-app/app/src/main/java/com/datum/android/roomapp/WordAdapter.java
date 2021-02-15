package com.datum.android.roomapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class WordAdapter extends RecyclerView.Adapter<WordAdapter.MyViewHolder> {

    private List<Words> mWordList = new ArrayList<>();
    private Context context;

    private OnItemClickListener mListener;

    public WordAdapter() {
    }

    public WordAdapter(List<Words> mWordList, Context context) {
        this.mWordList = mWordList;
        this.context = context;
    }

    public void setWords(List<Words> words) {
        mWordList = words;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.word_list_item, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Words currentWord = mWordList.get(position);
        holder.word.setText(currentWord.getWordName());
        holder.meaning.setText(currentWord.getWordMeaning());
        holder.type.setText(currentWord.getWordType());
    }

    @Override
    public int getItemCount() {
        return mWordList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView word, meaning, type;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            word = itemView.findViewById(R.id.word);
            meaning = itemView.findViewById(R.id.meaning);
            type = itemView.findViewById(R.id.type);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int index = getAdapterPosition();
                    mListener.onItemClick(mWordList.get(index));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Words word);
    }

    public void OnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    public Words getWordAt(int pos) {
        return mWordList.get(pos);
    }
}
