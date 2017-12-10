package com.example.philippe.gymtools.Module;

import com.example.philippe.gymtools.Objects.Exercise;

import java.util.List;

import io.reactivex.Single;

public interface ExerciseServiceInterface
{
	Single<List<Exercise>> getExercises(int planId);

	Single<Object> createExercise(Exercise exercise);

	Single<Object> deleteExercise(Exercise exercise);
}
