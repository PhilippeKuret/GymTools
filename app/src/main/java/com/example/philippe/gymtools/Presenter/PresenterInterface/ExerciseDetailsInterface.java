package com.example.philippe.gymtools.Presenter.PresenterInterface;

import android.content.Context;

import com.example.philippe.gymtools.Objects.Workout;

public interface ExerciseDetailsInterface
{
	void setDatabase(Context context);

	void getWorkouts(int exerciseId);

	void addWorkout(String name, double weight, int exerciseId);

	void deleteWorkout(Workout workout);
}
