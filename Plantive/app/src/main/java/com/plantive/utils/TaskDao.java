package com.plantive.utils;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.plantive.models.Task;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    long insertTask(Task task);

    @Update
    void updateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("SELECT * FROM tasks WHERE id = :taskId")
    Task getTaskById(int taskId);

    @Query("SELECT * FROM tasks WHERE plantId = :plantId")
    List<Task> getTasksByPlant(int plantId);

    @Query("SELECT * FROM tasks WHERE completed = 0")
    List<Task> getPendingTasks();

    @Query("SELECT * FROM tasks WHERE completed = 1")
    List<Task> getCompletedTasks();

    @Query("SELECT * FROM tasks WHERE scheduledDate BETWEEN :startDate AND :endDate")
    List<Task> getTasksByDateRange(long startDate, long endDate);
}
