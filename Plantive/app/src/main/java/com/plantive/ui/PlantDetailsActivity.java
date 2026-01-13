package com.plantive.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.plantive.R;
import com.google.android.material.button.MaterialButton;

public class PlantDetailsActivity extends AppCompatActivity {

    private ImageView plantImage;
    private TextView plantName;
    private TextView healthStatus;
    private MaterialButton btnWater;
    private MaterialButton btnFertilize;
    private RecyclerView careTimelineRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plant_details);

        initViews();
        setupListeners();
    }

    private void initViews() {
        plantImage = findViewById(R.id.image_plant);
        plantName = findViewById(R.id.text_plant_name);
        healthStatus = findViewById(R.id.text_health_status);
        btnWater = findViewById(R.id.btn_water);
        btnFertilize = findViewById(R.id.btn_fertilize);
        careTimelineRecyclerView = findViewById(R.id.recycler_care_timeline);

        careTimelineRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupListeners() {
        btnWater.setOnClickListener(v -> waterPlant());
        btnFertilize.setOnClickListener(v -> fertilizePlant());
    }

    private void waterPlant() {
        // TODO: Handle water action
    }

    private void fertilizePlant() {
        // TODO: Handle fertilize action
    }
}
