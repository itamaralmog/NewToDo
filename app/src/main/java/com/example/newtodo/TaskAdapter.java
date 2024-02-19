package com.example.newtodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private final ArrayList<TaskData> tasks;
    private final FragmentManager fragmentManager;

    public TaskAdapter(ArrayList<TaskData> tasks, FragmentManager fragmentManager) {
        this.tasks = tasks;
        this.fragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(tasks.get(position));
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public void addTask(TaskData task) {
        int m = task.getMonth();
//        task.setMonth(m+1);
        tasks.add(task);
        notifyItemInserted(tasks.size() - 1);
    }

    public TaskData getTask(String date, String desc) {
        for (TaskData task : tasks) {
            if (task.getTaskDes().equals(desc) && task.getDate().equals(date)) {
                return task;
            }
        }
        return null;
    }
    public void deleteTask(TaskData task) {
        int position = tasks.indexOf(task);
        if (position != -1) {
            tasks.remove(position);
            notifyItemRemoved(position);
        }
    }


    class TaskViewHolder extends RecyclerView.ViewHolder {
        private final TextView title;
        private final TextView description;
        private final TextView date;
        private final CheckBox active;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            date = itemView.findViewById(R.id.date);
            active = itemView.findViewById(R.id.active);
            itemView.findViewById(R.id.delete).setOnClickListener(view -> deleteTask(tasks.get(getAdapterPosition())));
        }

        public void bind(TaskData task) {
            title.setText(task.getTaskTitle());
            description.setText(task.getTaskDes());
            date.setText(task.getDate());
            active.setChecked(task.getActive());

            active.setOnClickListener(view -> {
                task.setActive(active.isChecked());
                Bundle result = new Bundle();
                result.putString("date", task.getDate());
                result.putString("description", task.getTaskDes());
                fragmentManager.setFragmentResult("datafromall", result);
                fragmentManager.setFragmentResult("delete_task", result);
            });
        }
    }
}


