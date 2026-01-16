package com.plantive.idp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class PlantsFragment extends Fragment {

    private List<Plant> allPlants;
    private PlantAdapter indoorAdapter, semiIndoorAdapter, outdoorAdapter;
    private List<Plant> indoorList, semiIndoorList, outdoorList;
    private static final String PREFS_NAME = "PlantivePrefs";
    private static final String KEY_MY_PLANTS = "MyPlantsList";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plants, container, false);

        loadPlants();

        RecyclerView rvIndoor = view.findViewById(R.id.rvIndoor);
        RecyclerView rvSemiIndoor = view.findViewById(R.id.rvSemiIndoor);
        RecyclerView rvOutdoor = view.findViewById(R.id.rvOutdoor);
        FloatingActionButton fabAddPlant = view.findViewById(R.id.fabAddPlant);

        indoorList = filterPlants("Indoor");
        semiIndoorList = filterPlants("Semi Indoor");
        outdoorList = filterPlants("Outdoor");

        indoorAdapter = new PlantAdapter(indoorList);
        semiIndoorAdapter = new PlantAdapter(semiIndoorList);
        outdoorAdapter = new PlantAdapter(outdoorList);

        setupRecyclerView(rvIndoor, indoorAdapter);
        setupRecyclerView(rvSemiIndoor, semiIndoorAdapter);
        setupRecyclerView(rvOutdoor, outdoorAdapter);

        fabAddPlant.setOnClickListener(v -> showAddPlantDialog());

        return view;
    }

    private void setupRecyclerView(RecyclerView rv, PlantAdapter adapter) {
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
    }

    private List<Plant> filterPlants(String category) {
        List<Plant> filtered = new ArrayList<>();
        if (allPlants == null) return filtered;
        for (Plant p : allPlants) {
            // Safe check for null categories from old saved data
            if (p.getCategory() != null && p.getCategory().equals(category)) {
                filtered.add(p);
            }
        }
        return filtered;
    }

    private void showAddPlantDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setTitle("Add New Plant");

        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_plant, null);
        final EditText etName = view.findViewById(R.id.etPlantName);
        final AutoCompleteTextView actvCategory = view.findViewById(R.id.actvCategory);

        String[] categories = {"Indoor", "Semi Indoor", "Outdoor"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_dropdown_item_1line, categories);
        actvCategory.setAdapter(adapter);

        builder.setView(view);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = etName.getText().toString();
            String cat = actvCategory.getText().toString();
            if (!name.isEmpty() && !cat.isEmpty()) {
                Plant newPlant = new Plant(name, "Healthy", ContextCompat.getColor(requireContext(), R.color.status_green), cat);
                allPlants.add(newPlant);
                updateListsAndAdapters(newPlant);
                savePlants();
            }
        });
        builder.setNegativeButton("Cancel", null);
        builder.show();
    }

    private void updateListsAndAdapters(Plant plant) {
        if (plant.getCategory().equals("Indoor")) {
            indoorList.add(plant);
            indoorAdapter.notifyItemInserted(indoorList.size() - 1);
        } else if (plant.getCategory().equals("Semi Indoor")) {
            semiIndoorList.add(plant);
            semiIndoorAdapter.notifyItemInserted(semiIndoorList.size() - 1);
        } else if (plant.getCategory().equals("Outdoor")) {
            outdoorList.add(plant);
            outdoorAdapter.notifyItemInserted(outdoorList.size() - 1);
        }
    }

    private void savePlants() {
        if (getContext() == null) return;
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(allPlants);
        editor.putString(KEY_MY_PLANTS, json);
        editor.apply();
    }

    private void loadPlants() {
        if (getContext() == null) return;
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString(KEY_MY_PLANTS, null);
        Type type = new TypeToken<ArrayList<Plant>>() {}.getType();
        allPlants = gson.fromJson(json, type);

        // Force reload the 25 plants if the list is small or categories are missing
        if (allPlants == null || allPlants.size() < 5) {
            allPlants = new ArrayList<>();
            int green = ContextCompat.getColor(requireContext(), R.color.status_green);
            int yellow = ContextCompat.getColor(requireContext(), R.color.status_yellow);
            
            // Indoor - 9 plants
            allPlants.add(new Plant("Monstera", "Healthy", green, "Indoor"));
            allPlants.add(new Plant("Snake Plant", "Healthy", green, "Indoor"));
            allPlants.add(new Plant("Pothos", "Healthy", green, "Indoor"));
            allPlants.add(new Plant("Peace Lily", "Needs Water", yellow, "Indoor"));
            allPlants.add(new Plant("Spider Plant", "Healthy", green, "Indoor"));
            allPlants.add(new Plant("ZZ Plant", "Healthy", green, "Indoor"));
            allPlants.add(new Plant("Philodendron", "Healthy", green, "Indoor"));
            allPlants.add(new Plant("Rubber Plant", "Healthy", green, "Indoor"));
            allPlants.add(new Plant("Fiddle Leaf Fig", "Healthy", green, "Indoor"));

            // Semi Indoor - 8 plants
            allPlants.add(new Plant("Bamboo Palm", "Healthy", green, "Semi Indoor"));
            allPlants.add(new Plant("Anthurium", "Healthy", green, "Semi Indoor"));
            allPlants.add(new Plant("Dracaena", "Healthy", green, "Semi Indoor"));
            allPlants.add(new Plant("Aglaonema", "Healthy", green, "Semi Indoor"));
            allPlants.add(new Plant("Calathea", "Needs Water", yellow, "Semi Indoor"));
            allPlants.add(new Plant("Begonia", "Healthy", green, "Semi Indoor"));
            allPlants.add(new Plant("Orchid", "Healthy", green, "Semi Indoor"));
            allPlants.add(new Plant("Boston Fern", "Healthy", green, "Semi Indoor"));

            // Outdoor - 8 plants
            allPlants.add(new Plant("Aloe Vera", "Healthy", green, "Outdoor"));
            allPlants.add(new Plant("Rose", "Healthy", green, "Outdoor"));
            allPlants.add(new Plant("Hibiscus", "Healthy", green, "Outdoor"));
            allPlants.add(new Plant("Lavender", "Healthy", green, "Outdoor"));
            allPlants.add(new Plant("Rosemary", "Healthy", green, "Outdoor"));
            allPlants.add(new Plant("Basil", "Healthy", green, "Outdoor"));
            allPlants.add(new Plant("Mint", "Needs Water", yellow, "Outdoor"));
            allPlants.add(new Plant("Tomato", "Healthy", green, "Outdoor"));
            
            savePlants();
        }
    }
}
