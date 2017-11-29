package com.example.philippe.gymtools.Presenter;

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

	public void setDatabase(DatabaseService database)
	{
		db = database;
	}

	public void getTrainingPlans()
	{
		db.getTrainingPlans().subscribeWith(new DisposableSingleObserver<List<TrainingPlan>>()
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

	public void createTrainingPlan(String name, Boolean isShown)
	{
		db.createTrainingPlan(new TrainingPlan(name, isShown)).subscribeWith(new DisposableSingleObserver<Long>()
		{
			@Override
			public void onSuccess(Long aLong)
			{
				//TODO create newplandetailactivity
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
