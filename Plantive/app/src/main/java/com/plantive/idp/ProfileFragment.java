package com.plantive.idp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;

public class ProfileFragment extends Fragment {

    private EditText etName, etAge, etBio;
    private static final String PREFS_NAME = "PlantivePrefs";
    private static final String KEY_NAME = "userName";
    private static final String KEY_AGE = "userAge";
    private static final String KEY_BIO = "userBio";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        etName = view.findViewById(R.id.etProfileName);
        etAge = view.findViewById(R.id.etProfileAge);
        etBio = view.findViewById(R.id.etProfileBio);
        MaterialButton btnSave = view.findViewById(R.id.btnSaveProfile);

        loadProfileData();

        btnSave.setOnClickListener(v -> {
            saveProfileData();
            Toast.makeText(requireContext(), "Profile updated successfully!", Toast.LENGTH_SHORT).show();
        });

        return view;
    }

    private void saveProfileData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(KEY_NAME, etName.getText().toString());
        editor.putString(KEY_AGE, etAge.getText().toString());
        editor.putString(KEY_BIO, etBio.getText().toString());
        editor.apply();
    }

    private void loadProfileData() {
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

        etName.setText(sharedPreferences.getString(KEY_NAME, ""));
        etAge.setText(sharedPreferences.getString(KEY_AGE, ""));
        etBio.setText(sharedPreferences.getString(KEY_BIO, ""));
    }
}
