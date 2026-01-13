package com.plantive.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.plantive.R;
import com.plantive.ui.adapters.OnboardingAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class WelcomeActivity extends AppCompatActivity {

    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private Button btnGetStarted;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        viewPager = findViewById(R.id.viewpager_onboarding);
        tabLayout = findViewById(R.id.tab_layout_onboarding);
        btnGetStarted = findViewById(R.id.btn_get_started);
        btnLogin = findViewById(R.id.btn_login);

        // Setup onboarding carousel
        setupOnboarding();

        // Button listeners
        btnGetStarted.setOnClickListener(v -> startMainActivity());
        btnLogin.setOnClickListener(v -> startMainActivity());
    }

    private void setupOnboarding() {
        OnboardingAdapter adapter = new OnboardingAdapter(this);
        viewPager.setAdapter(adapter);

        new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    // Tab setup if needed
                }).attach();
    }

    private void startMainActivity() {
        Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
