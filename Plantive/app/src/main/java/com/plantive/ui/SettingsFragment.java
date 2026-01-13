package com.plantive.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plantive.R;

public class SettingsFragment extends Fragment {

    private RecyclerView settingsRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        settingsRecyclerView = view.findViewById(R.id.recycler_settings);
        settingsRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // TODO: Setup settings adapter
    }
}
