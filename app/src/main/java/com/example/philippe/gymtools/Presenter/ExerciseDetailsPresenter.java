package com.example.philippe.gymtools.Presenter;

import android.content.Context;

import com.example.philippe.gymtools.Module.WorkoutService;
import com.example.philippe.gymtools.Module.WorkoutServiceInterface;
import com.example.philippe.gymtools.Objects.Workout;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class ExerciseDetailsPresenter
{
	private WorkoutServiceInterface db;

	public void setDatabase(Context context)
	{
		db = new WorkoutService(context);
	}

	public void getWorkouts(int exerciseId)
	{
		db.getWorkouts(exerciseId).subscribeWith(new DisposableSingleObserver<List<Workout>>()
		{
			@Override
			public void onSuccess(List<Workout> workouts)
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

	public void addWorkout(String name, double weight, int exerciseId)
	{
		db.createWorkout(new Workout(0, name, weight, exerciseId)).subscribeWith(new DisposableSingleObserver<Object>()
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

	public void deleteWorkout(Workout workout)
	{
		db.deleteWorkout(workout).subscribeWith(new DisposableSingleObserver<Object>()
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
