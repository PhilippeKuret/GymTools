package com.example.philippe.gymtools.Module.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.philippe.gymtools.Objects.Exercise;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface ExerciseDao
{
	@Insert
	void insertExercise(Exercise exercise);

	@Delete
	void deleteExercise(Exercise exercise);

	@Query("SELECT * FROM exercise WHERE plan_id = :trainingPlan_id")
	Single<List<Exercise>> getExercises(int trainingPlan_id);
}
