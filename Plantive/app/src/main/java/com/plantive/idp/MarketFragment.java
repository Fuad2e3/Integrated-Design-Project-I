package com.plantive.idp;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.card.MaterialCardView;
import java.util.ArrayList;
import java.util.List;

public class MarketFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_market, container, false);

        MaterialCardView cvSell = view.findViewById(R.id.cvSellPlant);
        RecyclerView rvMarket = view.findViewById(R.id.rvMarketPlants);

        cvSell.setOnClickListener(v -> Toast.makeText(getContext(), "Post your plant for sale coming soon!", Toast.LENGTH_SHORT).show());

        List<Plant> marketPlants = new ArrayList<>();
        int priceColor = Color.parseColor("#4CAF50");
        marketPlants.add(new Plant("Snake Plant", "৳ 450", priceColor, "Market"));
        marketPlants.add(new Plant("Aloe Vera", "৳ 250", priceColor, "Market"));
        marketPlants.add(new Plant("Monstera", "৳ 1200", priceColor, "Market"));
        marketPlants.add(new Plant("Peace Lily", "৳ 350", priceColor, "Market"));
        marketPlants.add(new Plant("Cactus", "৳ 150", priceColor, "Market"));
        marketPlants.add(new Plant("Spider Plant", "৳ 200", priceColor, "Market"));

        PlantAdapter adapter = new PlantAdapter(marketPlants);
        rvMarket.setAdapter(adapter);
        rvMarket.setLayoutManager(new GridLayoutManager(requireContext(), 2));

        return view;
    }
}
