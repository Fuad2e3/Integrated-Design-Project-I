package com.plantive.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.plantive.models.Plant;

import java.util.List;

@Dao
public interface PlantDao {

    @Insert
    long insertPlant(Plant plant);

    @Update
    void updatePlant(Plant plant);

    @Delete
    void deletePlant(Plant plant);

    @Query("SELECT * FROM plants WHERE id = :plantId")
    Plant getPlantById(int plantId);

    @Query("SELECT * FROM plants")
    List<Plant> getAllPlants();

    @Query("SELECT * FROM plants WHERE healthStatus = :status")
    List<Plant> getPlantsByHealth(String status);

    @Query("SELECT * FROM plants WHERE location = :location")
    List<Plant> getPlantsByLocation(String location);
}
