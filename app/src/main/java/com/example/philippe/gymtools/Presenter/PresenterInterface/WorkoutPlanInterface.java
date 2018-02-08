package com.example.philippe.gymtools.Presenter.PresenterInterface;

import android.content.Context;

import com.example.philippe.gymtools.Activities.ViewInterface.WorkoutPlanView;
import com.example.philippe.gymtools.Notification.TrainingPlanNotification;
import com.example.philippe.gymtools.Objects.TrainingPlan;

public interface WorkoutPlanInterface
{
	void getDisplayedTrainingPlans();

	void setDatabase(Context context);

	void setView(WorkoutPlanView workoutPlanView);

	void deleteTrainingPlan(TrainingPlan trainingPlan);
}
