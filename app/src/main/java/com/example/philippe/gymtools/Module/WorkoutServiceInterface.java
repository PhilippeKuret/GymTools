package com.example.philippe.gymtools.Module;

import com.example.philippe.gymtools.Objects.Workout;

import java.util.List;

import io.reactivex.Single;

public interface WorkoutServiceInterface
{
	Single<List<Workout>> getWorkouts(int exerciseId);

	Single<Object> createWorkout(Workout workout);

	Single<Object> deleteWorkout(Workout workout);

	Single<Object> updateMultipleWorkouts(List<Workout> workouts);
}
