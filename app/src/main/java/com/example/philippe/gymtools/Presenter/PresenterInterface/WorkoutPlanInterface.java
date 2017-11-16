package com.example.philippe.gymtools.Presenter.PresenterInterface;

import com.example.philippe.gymtools.Activities.ViewInterface.WorkoutPlanView;
import com.example.philippe.gymtools.Module.DatabaseService;

public interface WorkoutPlanInterface
{
	void getTrainingPlans();

	void setDatabase(DatabaseService database);

	void setView(WorkoutPlanView workoutPlanView);
}
