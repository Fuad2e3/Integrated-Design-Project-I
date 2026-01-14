package com.plantive.idp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private List<Plant> plants;

    public PlantAdapter(List<Plant> plants) {
        this.plants = plants;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_plant_health, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Plant plant = plants.get(position);
        holder.tvName.setText(plant.getName());
        holder.tvStatus.setText(plant.getStatus());
        holder.vStatus.setBackgroundColor(plant.getStatusColor());
    }

    @Override
    public int getItemCount() {
        return plants.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvStatus;
        View vStatus;

        ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvPlantName);
            tvStatus = itemView.findViewById(R.id.tvStatus);
            vStatus = itemView.findViewById(R.id.vStatusIndicator);
        }
    }
}
