package com.example.philippe.gymtools.Presenter;

import android.content.Context;

import com.example.philippe.gymtools.Activities.ViewInterface.PlanDetailsView;
import com.example.philippe.gymtools.Module.ExerciseService;
import com.example.philippe.gymtools.Module.ExerciseServiceInterface;
import com.example.philippe.gymtools.Objects.Exercise;
import com.example.philippe.gymtools.Presenter.PresenterInterface.PlanDetailsInterface;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class PlanDetailsPresenter implements PlanDetailsInterface
{
	private ExerciseServiceInterface db;

	private PlanDetailsView planDetailsView;

	public void setDatabase(Context context)
	{
		db = new ExerciseService(context);
	}

	public void setView(PlanDetailsView planDetailsView)
	{
		this.planDetailsView = planDetailsView;
	}

	public void getExercises(int planId)
	{
		db.getExercises(planId).subscribeWith(new DisposableSingleObserver<List<Exercise>>()
		{
			@Override
			public void onSuccess(List<Exercise> exercises)
			{
				dispose();
			}

			@Override
			public void onError(Throwable e)
			{
				e.printStackTrace();
				dispose();
			}
		});
	}

	public void createExercise(String name, int planId)
	{
		db.createExercise(new Exercise(0, name, planId)).subscribeWith(new DisposableSingleObserver<Object>()
		{
			@Override
			public void onSuccess(Object o)
			{
				dispose();
			}

			@Override
			public void onError(Throwable e)
			{
				e.printStackTrace();
				dispose();
			}
		});
	}
}
