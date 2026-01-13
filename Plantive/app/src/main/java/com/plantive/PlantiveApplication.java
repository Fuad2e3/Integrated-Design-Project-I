package com.plantive;

import android.app.Application;

import com.plantive.utils.PlantiveDatabase;

public class PlantiveApplication extends Application {

    private static PlantiveDatabase database;

    @Override
    public void onCreate() {
        super.onCreate();
        database = PlantiveDatabase.getInstance(this);
    }

    public static PlantiveDatabase getDatabase() {
        return database;
    }
}
