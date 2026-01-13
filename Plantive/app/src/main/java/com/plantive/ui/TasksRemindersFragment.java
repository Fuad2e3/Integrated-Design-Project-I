package com.plantive.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plantive.R;
import com.google.android.material.tabs.TabLayout;

public class TasksRemindersFragment extends Fragment {

    private TabLayout tabLayout;
    private RecyclerView tasksRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_tasks_reminders, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tabLayout = view.findViewById(R.id.tab_layout_tasks);
        tasksRecyclerView = view.findViewById(R.id.recycler_tasks);

        // Setup tabs
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tasks_today));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tasks_upcoming));
        tabLayout.addTab(tabLayout.newTab().setText(R.string.tasks_completed));

        // Setup RecyclerView
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // Tab selection listener
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                filterTasksByTab(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void filterTasksByTab(int position) {
        // TODO: Filter tasks based on tab selection
    }
}
