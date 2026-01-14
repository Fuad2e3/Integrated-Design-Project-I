package com.plantive.idp;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        setupWindowInsets();
        setupBottomNavigation();
    }

    private void setupWindowInsets() {
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            // We want the content to be under the status bar but above the bottom nav
            v.setPadding(0, systemBars.top, 0, 0);
            
            // Adjust BottomNavigationView padding to handle system navigation bar
            BottomNavigationView navView = findViewById(R.id.bottomNavigation);
            if (navView != null) {
                navView.setPadding(0, 0, 0, systemBars.bottom);
            }
            
            return insets;
        });
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
