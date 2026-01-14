package com.plantive.idp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupWindowInsets();
        setupBottomNavigation();
        setupDashboardData();
    }

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(0, systemBars.top, 0, 0);
            
            BottomNavigationView navView = findViewById(R.id.bottomNavigation);
            if (navView != null) {
                navView.setPadding(0, 0, 0, systemBars.bottom);
            }
            
            return insets;
        });
    }

    private void setupDashboardData() {
        // Setup Plant Health RecyclerView
        RecyclerView rvPlantHealth = findViewById(R.id.rvPlantHealth);
        List<Plant> plants = new ArrayList<>();
        plants.add(new Plant("Monstera", "Healthy", ContextCompat.getColor(this, R.color.status_green)));
        plants.add(new Plant("Snake Plant", "Needs Water", ContextCompat.getColor(this, R.color.status_yellow)));
        plants.add(new Plant("Aloe Vera", "Urgent Care", ContextCompat.getColor(this, R.color.status_red)));
        
        PlantAdapter plantAdapter = new PlantAdapter(plants);
        rvPlantHealth.setAdapter(plantAdapter);
        rvPlantHealth.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Setup Today's Tasks RecyclerView
        RecyclerView rvTasks = findViewById(R.id.rvTasks);
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task("Water Monstera", "200ml • Room temp", false));
        tasks.add(new Task("Fertilize Aloe", "Organic mix", false));
        tasks.add(new Task("Rotate Snake Plant", "Better sunlight", true));
        
        TaskAdapter taskAdapter = new TaskAdapter(tasks);
        rvTasks.setAdapter(taskAdapter);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setupBottomNavigation() {
        BottomNavigationView bottomNav = findViewById(R.id.bottomNavigation);
        bottomNav.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();
            if (itemId == R.id.navigation_home) {
                return true;
            } else if (itemId == R.id.navigation_plants) {
                showPlaceholder("My Plants");
                return true;
            } else if (itemId == R.id.navigation_tasks) {
                showPlaceholder("Tasks");
                return true;
            } else if (itemId == R.id.navigation_ai) {
                showPlaceholder("AI Assistant");
                return true;
            } else if (itemId == R.id.navigation_profile) {
                showPlaceholder("Profile");
                return true;
            }
            return false;
        });
    }

    private void showPlaceholder(String screenName) {
        Toast.makeText(this, screenName + " coming soon!", Toast.LENGTH_SHORT).show();
    }
}
