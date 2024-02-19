package com.example.newtodo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FragmentCompleted extends Fragment {

    private TaskAdapter taskAdapter;

    public FragmentCompleted() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_completed, container, false);

        initRecyclerView(view);
        setupFragmentResultListener();

        return view;
    }

    private void initRecyclerView(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.allCompleted);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<TaskData> completedTasks = new ArrayList<>();
        taskAdapter = new TaskAdapter(completedTasks, getParentFragmentManager());
        recyclerView.setAdapter(taskAdapter);
    }

    private void setupFragmentResultListener() {
        getParentFragmentManager().setFragmentResultListener("datafromall", this, (requestKey, result) -> {
            String date = result.getString("date");
            String desc = result.getString("description");
            addCompletedTask(new TaskData("", desc, date, false));
        });
    }

    private void addCompletedTask(TaskData task) {
        taskAdapter.addTask(task);
    }
}
