package com.plantive.idp;

public class Plant {
    private String name;
    private String status;
    private int statusColor;

    public Plant(String name, String status, int statusColor) {
        this.name = name;
        this.status = status;
        this.statusColor = statusColor;
    }

    public String getName() { return name; }
    public String getStatus() { return status; }
    public int getStatusColor() { return statusColor; }
}
