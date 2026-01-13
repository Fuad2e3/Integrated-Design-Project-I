package com.plantive.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plantive.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MyPlantsFragment extends Fragment {

    private RecyclerView plantsGridRecyclerView;
    private FloatingActionButton fabAddPlant;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_my_plants, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        plantsGridRecyclerView = view.findViewById(R.id.recycler_plants_grid);
        fabAddPlant = view.findViewById(R.id.fab_add_plant);

        // Setup GridLayout
        plantsGridRecyclerView.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        // Add plant button
        fabAddPlant.setOnClickListener(v -> addNewPlant());

        // TODO: Setup adapter and load data
    }

    private void addNewPlant() {
        // TODO: Open add plant dialog/activity
    }
}
