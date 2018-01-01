package com.example.philippe.gymtools.Presenter.PresenterInterface;

import android.content.Context;

import com.example.philippe.gymtools.Activities.ViewInterface.PlanDetailsView;
import com.example.philippe.gymtools.Objects.Exercise;

public interface PlanDetailsInterface
{
	void setDatabase(Context context);

	void getExercises(int planId);

	void createExercise(String name, int planId);

	void deleteExercise(Exercise exercise);

	void setView(PlanDetailsView planDetailsView);
}
