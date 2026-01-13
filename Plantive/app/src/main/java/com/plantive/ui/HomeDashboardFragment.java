package com.plantive.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plantive.R;

public class HomeDashboardFragment extends Fragment {

    private RecyclerView plantsRecyclerView;
    private RecyclerView tasksRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home_dashboard, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plantsRecyclerView = view.findViewById(R.id.recycler_plants);
        tasksRecyclerView = view.findViewById(R.id.recycler_tasks);

        // Setup RecyclerViews
        plantsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(),
                LinearLayoutManager.HORIZONTAL, false));
        tasksRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // TODO: Setup adapters and load data
    }
}
