package com.example.philippe.gymtools.Presenter;

import com.example.philippe.gymtools.Activities.ViewInterface.WorkoutPlanView;
import com.example.philippe.gymtools.Activities.WorkoutPlanActivity;
import com.example.philippe.gymtools.Module.DatabaseService;
import com.example.philippe.gymtools.Presenter.PresenterInterface.WorkoutPlanInterface;

public class WorkoutPlanPresenter implements WorkoutPlanInterface
{
	private WorkoutPlanView workoutPlanView;
	private DatabaseService db;

	public void setView(WorkoutPlanView workoutPlanView)
	{
		this.workoutPlanView = workoutPlanView;
	}

	public void setDatabase(DatabaseService database)
	{
		db = database;
	}

	public void getTrainingPlans()
	{
		db.getTrainingPlans()
				.subscribe(response -> {
					workoutPlanView.setListView(response);
		});
	}
}
