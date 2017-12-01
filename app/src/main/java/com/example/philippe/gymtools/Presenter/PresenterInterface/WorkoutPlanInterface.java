package com.example.philippe.gymtools.Presenter.PresenterInterface;

import android.content.Context;

import com.example.philippe.gymtools.Activities.ViewInterface.WorkoutPlanView;

public interface WorkoutPlanInterface
{
	void getDisplayedTrainingPlans();

	void setDatabase(Context context);

	void setView(WorkoutPlanView workoutPlanView);
}
