package com.plantive.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plantive.R;
import com.google.android.material.textfield.TextInputEditText;

public class AIAssistantFragment extends Fragment {

    private RecyclerView chatRecyclerView;
    private TextInputEditText inputQuestion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_ai_assistant, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        chatRecyclerView = view.findViewById(R.id.recycler_chat);
        inputQuestion = view.findViewById(R.id.input_question);

        // Setup chat RecyclerView
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        // TODO: Setup adapter and chat functionality
    }
}
