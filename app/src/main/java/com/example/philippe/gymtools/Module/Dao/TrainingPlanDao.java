package com.example.philippe.gymtools.Module.Dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import java.util.List;

import io.reactivex.Single;

import static android.arch.persistence.room.OnConflictStrategy.FAIL;
import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

@Dao
public interface TrainingPlanDao
{
	@Insert(onConflict = FAIL)
	Single<Long> insertTrainingPlan(TrainingPlan trainingPlan);

	@Query("SELECT * FROM  training_plan")
	Single<List<TrainingPlan>> getTrainingPlans();
}
