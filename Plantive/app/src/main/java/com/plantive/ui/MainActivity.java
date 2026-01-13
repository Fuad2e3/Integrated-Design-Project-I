package com.plantive.ui;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import com.plantive.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavView;
    private NavController navController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavView = findViewById(R.id.bottom_nav);

        // If using navigation component
        // navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        // NavigationUI.setupWithNavController(bottomNavView, navController);

        // Manual navigation
        bottomNavView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        // Load home fragment by default
        if (savedInstanceState == null) {
            loadFragment(new HomeDashboardFragment());
        }
    }

    private boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;

        if (item.getItemId() == R.id.nav_home) {
            fragment = new HomeDashboardFragment();
        } else if (item.getItemId() == R.id.nav_plants) {
            fragment = new MyPlantsFragment();
        } else if (item.getItemId() == R.id.nav_tasks) {
            fragment = new TasksRemindersFragment();
        } else if (item.getItemId() == R.id.nav_ai) {
            fragment = new AIAssistantFragment();
        } else if (item.getItemId() == R.id.nav_settings) {
            fragment = new SettingsFragment();
        }

        return loadFragment(fragment);
    }

    private boolean loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }
}
