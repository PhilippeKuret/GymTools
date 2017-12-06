package com.example.philippe.gymtools.Presenter.PresenterInterface;

import android.content.Context;

import com.example.philippe.gymtools.Activities.ViewInterface.TrainingPlansView;
import com.example.philippe.gymtools.Objects.TrainingPlan;

public interface TrainingPlansInterface
{
	void getTrainingPlans();

	void setDatabase(Context context);

	void setView(TrainingPlansView trainingPlansView);

	void createTrainingPlan(String name, Boolean isShown);

	void updateMultipleTrainingPlans(TrainingPlan... trainingPlans);

	void deleteTrainingPlan(TrainingPlan trainingPlan);
}
