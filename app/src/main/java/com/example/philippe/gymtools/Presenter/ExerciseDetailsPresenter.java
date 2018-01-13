package com.example.philippe.gymtools.Presenter;

import android.content.Context;

import com.example.philippe.gymtools.Fragments.FragmentInterface.ExerciseDetailsFragmentView;
import com.example.philippe.gymtools.Module.WorkoutService;
import com.example.philippe.gymtools.Module.WorkoutServiceInterface;
import com.example.philippe.gymtools.Objects.Workout;
import com.example.philippe.gymtools.Presenter.PresenterInterface.ExerciseDetailsInterface;

import java.util.List;

import io.reactivex.observers.DisposableSingleObserver;

public class ExerciseDetailsPresenter implements ExerciseDetailsInterface
{
	private WorkoutServiceInterface db;

	private ExerciseDetailsFragmentView view;

	public void setDatabase(Context context)
	{
		db = new WorkoutService(context);
	}

	public void setView(ExerciseDetailsFragmentView exerciseDetailsFragmentView) {view = exerciseDetailsFragmentView; }

	public void getWorkouts(int exerciseId)
	{
		db.getWorkouts(exerciseId).subscribeWith(new DisposableSingleObserver<List<Workout>>()
		{
			@Override
			public void onSuccess(List<Workout> workouts)
			{
				view.setWorkoutsInView(workouts);
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

	public void addWorkout(Workout workout)
	{
		db.createWorkout(workout).subscribeWith(new DisposableSingleObserver<Object>()
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

	public void updateMultipleWorkouts(List<Workout> workouts)
	{
		db.updateMultipleWorkouts(workouts).subscribeWith(new DisposableSingleObserver<Object>()
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
