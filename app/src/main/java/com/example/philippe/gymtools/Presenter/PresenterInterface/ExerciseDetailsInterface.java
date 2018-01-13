package com.example.philippe.gymtools.Presenter.PresenterInterface;

import android.content.Context;

import com.example.philippe.gymtools.Fragments.FragmentInterface.ExerciseDetailsFragmentView;
import com.example.philippe.gymtools.Objects.Workout;

import java.util.List;

public interface ExerciseDetailsInterface
{
	void setDatabase(Context context);

	void setView(ExerciseDetailsFragmentView exerciseDetailsFragmentView);

	void getWorkouts(int exerciseId);

	void addWorkout(Workout workout);

	void deleteWorkout(Workout workout);

	void updateMultipleWorkouts(List<Workout> workouts);
}
