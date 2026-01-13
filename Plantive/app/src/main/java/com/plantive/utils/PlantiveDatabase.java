package com.plantive.utils;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.plantive.models.Plant;
import com.plantive.models.Task;

@Database(entities = {Plant.class, Task.class}, version = 1)
public abstract class PlantiveDatabase extends RoomDatabase {

    public abstract PlantDao plantDao();
    public abstract TaskDao taskDao();

    private static volatile PlantiveDatabase INSTANCE;

    public static PlantiveDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (PlantiveDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            PlantiveDatabase.class,
                            "plantive_db"
                    ).build();
                }
            }
        }
        return INSTANCE;
    }
}
