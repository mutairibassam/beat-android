package com.datum.android.todoapp2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.datum.android.todoapp2.R;
import com.datum.android.todoapp2.taskbatabase.TaskTable;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskAdapterViewHolder> {

    List<TaskTable> taskTableList;

    public TaskAdapter(List<TaskTable> taskTableList) {
        this.taskTableList = taskTableList;
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

        return new TaskAdapterViewHolder(view);
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

        public TaskAdapterViewHolder(@NonNull View itemView) {
            super(itemView);

            taskTitle = itemView.findViewById(R.id.task_title);
        }

        void bind(TaskTable taskTable) {
            taskTitle.setText(taskTable.getTask());

        }
    }
}
