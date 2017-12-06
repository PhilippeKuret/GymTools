package com.example.philippe.gymtools.Presenter;

import android.content.Context;

import com.example.philippe.gymtools.Activities.ViewInterface.TrainingPlansView;
import com.example.philippe.gymtools.Module.DatabaseService;
import com.example.philippe.gymtools.Module.DatabaseServiceInterface;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.PresenterInterface.TrainingPlansInterface;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class TrainingPlansPresenter implements TrainingPlansInterface
{
	private TrainingPlansView trainingPlansView;

	private DatabaseServiceInterface db;

	public void setView(TrainingPlansView trainingPlansView)
	{
		this.trainingPlansView = trainingPlansView;
	}

	public void setDatabase(Context context)
	{
		db = new DatabaseService(context);
	}

	public void getTrainingPlans()
	{
		db.getNotDisplayedTrainingPlans().subscribeWith(new DisposableSingleObserver<List<TrainingPlan>>()
		{
			@Override
			public void onSuccess(List<TrainingPlan> trainingPlans)
			{
				trainingPlansView.setSelectedPlansInView(trainingPlans);
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
		db.createTrainingPlan(new TrainingPlan(0, name, isShown)).subscribeWith(new DisposableSingleObserver<Object>()
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

	public void updateMultipleTrainingPlans(TrainingPlan... trainingPlans)
	{
		db.updateMultipleTrainingPlans(trainingPlans).subscribeWith(new DisposableSingleObserver<Object>()
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
