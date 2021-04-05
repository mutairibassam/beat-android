package com.datum.android.todoapp2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.todoapp2.R;
import com.datum.android.todoapp2.taskbatabase.TaskTable;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolder> {

    List<TaskTable> taskTableList;

    private OnItemFavListener mListener;

    public TaskAdapter(List<TaskTable> taskTableList, OnItemFavListener mListener) {
        this.taskTableList = taskTableList;
        this.mListener = mListener;
    }

    public interface OnItemFavListener {
        void onFavClick(TaskTable taskTable);
    }

    public void setTaskList(List<TaskTable> taskDataList) {
        this.taskTableList = taskDataList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public TaskAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.task_item, parent, false);

        return new TaskAdapterViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskAdapterViewHolder holder, int position) {
        holder.bind(taskTableList.get(position));
    }

    @Override
    public int getItemCount() {
        return taskTableList.size();
    }

    public static class TaskAdapterViewHolder extends RecyclerView.ViewHolder {

        TextView taskTitle;
        Button btn_fav;
        OnItemFavListener mListener;

        public TaskAdapterViewHolder(@NonNull View itemView, OnItemFavListener mListener) {
            super(itemView);

            taskTitle = itemView.findViewById(R.id.task_title);
            btn_fav = itemView.findViewById(R.id.btn_fav);
            this.mListener = mListener;
        }


        void bind(TaskTable taskTable) {

            /**
             *      we duplicate the logic for the first initialization
             *
             */
            if(taskTable.isImportant()) {
                btn_fav.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favorite_selected));
            } else {
                btn_fav.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favorite_unselected));
            }

            taskTitle.setText(taskTable.getTask());
            btn_fav.setOnClickListener(View -> {

                /**
                 *      we update the important value in the database based on the user action
                 *
                 */

                if(!taskTable.isImportant()) {
                    btn_fav.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favorite_selected));
                    taskTable.setIsImportant(true);
                } else {
                    btn_fav.setBackground(ContextCompat.getDrawable(itemView.getContext(), R.drawable.ic_favorite_unselected));
                    taskTable.setIsImportant(false);
                }

                mListener.onFavClick(taskTable);
            });
        }
    }

    public TaskTable getTaskAt(int pos) {
        return taskTableList.get(pos);
    }
}
