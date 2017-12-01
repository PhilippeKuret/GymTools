package com.example.philippe.gymtools.Module.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import java.util.List;

import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.FAIL;

@Dao
public interface TrainingPlanDao
{
	@Insert(onConflict = FAIL)
	void insertTrainingPlan(TrainingPlan trainingPlan);

	@Query("SELECT * FROM training_plan")
	Single<List<TrainingPlan>> getTrainingPlans();

	@Query("SELECT * FROM  training_plan WHERE is_displayed = :isDisplayed")
	Single<List<TrainingPlan>> getDisplayedTrainingPlans(Boolean isDisplayed);
}