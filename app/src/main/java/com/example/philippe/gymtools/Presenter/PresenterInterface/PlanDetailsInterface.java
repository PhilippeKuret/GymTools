package com.example.philippe.gymtools.Presenter.PresenterInterface;

import android.content.Context;

import com.example.philippe.gymtools.Activities.ViewInterface.PlanDetailsView;

public interface PlanDetailsInterface
{
	void setDatabase(Context context);

	void getExercises(int planId);

	void createExercise(String name, int planId);

	void setView(PlanDetailsView planDetailsView);
}
