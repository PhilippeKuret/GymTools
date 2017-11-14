package com.example.philippe.gymtools.Presenter;

import android.content.Context;

import com.example.philippe.gymtools.Activities.WorkoutPlanActivity;
import com.example.philippe.gymtools.Module.DatabaseService;
import com.example.philippe.gymtools.Objects.TrainingPlan;

public class WorkoutPlanPresenter
{
	WorkoutPlanActivity workoutPlanActivity;
	DatabaseService db;

	public WorkoutPlanPresenter(WorkoutPlanActivity activity, DatabaseService databaseService)
	{
		workoutPlanActivity = activity;
		db = databaseService;
	}

	public void getTrainingPlans()
	{
		db.getTrainingPlans()
				.subscribe(response -> {
					workoutPlanActivity.setListView(response);
		});
	}

	public void insertTrainingPlan(TrainingPlan trainingPlan)
	{
		db.insertTrainingPlan(trainingPlan);
	}
}
