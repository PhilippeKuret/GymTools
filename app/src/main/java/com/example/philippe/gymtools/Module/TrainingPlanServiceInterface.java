package com.example.philippe.gymtools.Module;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import java.util.List;

import io.reactivex.Single;

public interface TrainingPlanServiceInterface
{
	Single<List<TrainingPlan>> getDisplayedTrainingPlans();

	Single<List<TrainingPlan>> getNotDisplayedTrainingPlans();

	Single<Object> createTrainingPlan(TrainingPlan trainingPlan);

	Single<Object> deleteTrainingPlan(TrainingPlan trainingPlan);

	Single<Object> updateMultipleTrainingPlans(TrainingPlan... trainingPlans);
}
