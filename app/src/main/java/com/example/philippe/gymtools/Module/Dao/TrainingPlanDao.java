package com.example.philippe.gymtools.Module.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import io.reactivex.Single;

@Dao
public interface TrainingPlanDao
{
	@Query("SELECT * FROM  training_plan")
	Single<TrainingPlan[]> getTrainingPlans();
}
