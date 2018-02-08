package com.example.philippe.gymtools.Presenter.PresenterInterface;


import android.content.Context;

import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Objects.Workout;

import java.util.List;

public interface NotificationInterface
{
	void setTrainingPlanNotification(Context context);

	void setExerciseWorkoutNotification(Context context);

	void getNotificationDisplayedTrainingPlans();

	void getNotificationExerciseWorkouts(TrainingPlan trainingPlan);

	void updateNotificationWorkouts(List<Workout> workouts);
}
