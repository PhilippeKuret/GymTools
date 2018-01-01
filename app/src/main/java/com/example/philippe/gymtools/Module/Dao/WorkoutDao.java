package com.example.philippe.gymtools.Module.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.philippe.gymtools.Objects.Workout;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface WorkoutDao
{
	@Insert
	void insertWorkout(Workout set);

	@Delete
	void deleteWorkout(Workout set);

	@Query("SELECT * FROM  WHERE exercise_id = :exerciseId")
	Single<List<Workout>> getWorkout(int exerciseId);
}
