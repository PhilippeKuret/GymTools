package com.example.philippe.gymtools.Presenter;

import android.content.Context;

import com.example.philippe.gymtools.Activities.ViewInterface.WorkoutPlanView;
import com.example.philippe.gymtools.Module.DatabaseService;
import com.example.philippe.gymtools.Module.DatabaseServiceInterface;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.PresenterInterface.WorkoutPlanInterface;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class WorkoutPlanPresenter implements WorkoutPlanInterface
{
	private WorkoutPlanView workoutPlanView;

	private DatabaseServiceInterface db;

	public void setView(WorkoutPlanView workoutPlanView)
	{
		this.workoutPlanView = workoutPlanView;
	}

	public void setDatabase(Context context)
	{
		db = new DatabaseService(context);
	}

	public void getDisplayedTrainingPlans()
	{
		db.getDisplayedTrainingPlans().subscribeWith(new DisposableSingleObserver<List<TrainingPlan>>()
		{

			@Override
			public void onSuccess(List<TrainingPlan> trainingPlans)
			{
				workoutPlanView.setSelectedPlansInView(trainingPlans);
				dispose();
			}

			@Override
			public void onError(Throwable e)
			{
				//TODO
				e.printStackTrace();
				dispose();
			}
		});
	}

	public void deleteTrainingPlan(TrainingPlan trainingPlan)
	{
		db.deleteTrainingPlan(trainingPlan).subscribeWith(new DisposableSingleObserver<Object>()
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
