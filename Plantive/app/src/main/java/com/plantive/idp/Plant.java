package com.plantive.idp;

public class Plant {
    private String name;
    private String status;
    private int statusColor;
    private String category; // Indoor, Semi Indoor, Outdoor

    public Plant(String name, String status, int statusColor, String category) {
        this.name = name;
        this.status = status;
        this.statusColor = statusColor;
        this.category = category;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getStatusColor() { return statusColor; }
    public void setStatusColor(int statusColor) { this.statusColor = statusColor; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
}
