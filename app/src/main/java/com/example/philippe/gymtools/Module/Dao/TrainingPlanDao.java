package com.example.philippe.gymtools.Module.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface TrainingPlanDao
{
	@Query("SELECT * FROM  training_plan")
	Single<List<TrainingPlan>> getTrainingPlans();

	@Insert
	void insertPlan(TrainingPlan trainingPlan);
}
