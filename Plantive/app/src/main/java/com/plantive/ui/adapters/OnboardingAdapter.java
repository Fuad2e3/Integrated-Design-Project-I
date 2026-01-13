package com.plantive.ui.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class OnboardingAdapter extends FragmentStateAdapter {

    public OnboardingAdapter(FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return onboarding fragments for each position
        // These would be simple fragments showing the benefits
        return new Fragment();
    }

    @Override
    public int getItemCount() {
        return 3; // Three onboarding cards
    }
}
