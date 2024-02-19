package com.example.newtodo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentAll extends Fragment implements MyDialog {

    private TaskAdapter taskAdapter;

    public FragmentAll() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all, container, false);

        setupRecyclerView(view);
        setupAddButton(view);

        return view;
    }

    private void setupRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.all);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<TaskData> tasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(tasks, getParentFragmentManager());
        recyclerView.setAdapter(taskAdapter);
    }

    private void setupAddButton(View view) {
        Button addButton = view.findViewById(R.id.add);
        addButton.setOnClickListener(buttonView -> openDialog());
    }

    private void openDialog() {
        AddTaskDialog addTaskDialog = new AddTaskDialog();
        addTaskDialog.show(getChildFragmentManager(), "dialog");
    }

    @Override
    public void onDialogPositiveClick(TaskData data) {
//        int m = data.getMonth();
        taskAdapter.addTask(data);
        notifyNewActiveTask(data.getDate(), data.getTaskDes());
    }

    @Override
    public void onDialogNegativeClick() {
        // Handle negative dialog button click if needed
    }

    private void notifyNewActiveTask(String date, String description) {
        Bundle result = new Bundle();
        result.putString("date", date);
        result.putString("description", description);
        getParentFragmentManager().setFragmentResult("new_active_task", result);
    }
}