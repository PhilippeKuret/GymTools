package com.example.philippe.gymtools.Fragments.FragmentInterface;


import com.example.philippe.gymtools.Objects.Exercise;
import com.example.philippe.gymtools.Objects.Workout;

import java.util.List;

public interface ExerciseDetailsFragmentView
{
	void createBundle(Exercise exercise);

	void setWorkoutsInView(List<Workout> workouts);
}
