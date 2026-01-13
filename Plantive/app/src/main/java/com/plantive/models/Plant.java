package com.plantive.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plants")
public class Plant {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private String type;
    private String imageUrl;
    private String location;
    private String healthStatus; // "healthy", "warning", "critical"
    private long dateAdded;
    private int waterFrequencyDays;
    private int fertilizeFrequencyDays;

    // Constructors
    public Plant() {
    }

    public Plant(String name, String type, String location) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.healthStatus = "healthy";
        this.dateAdded = System.currentTimeMillis();
        this.waterFrequencyDays = 7;
        this.fertilizeFrequencyDays = 30;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public int getWaterFrequencyDays() {
        return waterFrequencyDays;
    }

    public void setWaterFrequencyDays(int waterFrequencyDays) {
        this.waterFrequencyDays = waterFrequencyDays;
    }

    public int getFertilizeFrequencyDays() {
        return fertilizeFrequencyDays;
    }

    public void setFertilizeFrequencyDays(int fertilizeFrequencyDays) {
        this.fertilizeFrequencyDays = fertilizeFrequencyDays;
    }
}
