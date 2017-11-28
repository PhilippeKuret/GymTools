package com.example.philippe.gymtools.Module;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import java.util.List;

import io.reactivex.Single;

public interface DatabaseServiceInterface
{
	Single<List<TrainingPlan>> getTrainingPlans();
}
