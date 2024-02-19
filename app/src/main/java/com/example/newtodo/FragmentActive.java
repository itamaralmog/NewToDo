package com.example.newtodo;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentActive extends Fragment {

    private TaskAdapter taskAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Listen for the "new_active_task" event
        getParentFragmentManager().setFragmentResultListener("new_active_task", this, this::handleNewActiveTask);

        // Listen for the "delete_task" event
        getParentFragmentManager().setFragmentResultListener("delete_task", this, this::handleDeleteTask);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_active, container, false);

        // Configure RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.Allactive);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Set up adapter
        ArrayList<TaskData> tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(tasks, getParentFragmentManager());
        recyclerView.setAdapter(taskAdapter);

        return view;
    }

    // Handle "new_active_task" event
    private void handleNewActiveTask(String requestKey, Bundle result) {
        String date = result.getString("date");
        String desc = result.getString("description");
        Log.d("FragmentActive date",date+ " FragmentActive date");
        addTask(new TaskData("", desc, date, true));
    }

    // Handle "delete_task" event
    private void handleDeleteTask(String requestKey, Bundle result) {
        String date = result.getString("date");
        String desc = result.getString("description");
        TaskData task = taskAdapter.getTask(date, desc);
        deleteTask(task);
    }

    // Add task to the adapter and notify data set changed
    private void addTask(TaskData task) {
        taskAdapter.addTask(task);
    }

    // Delete task from the adapter and notify data set changed
    private void deleteTask(TaskData task) {
        taskAdapter.deleteTask(task);
    }
}
