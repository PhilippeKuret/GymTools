package com.example.philippe.gymtools.Module;

import android.content.Context;

import com.example.philippe.gymtools.Objects.Workout;

import java.util.List;

import javax.inject.Singleton;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class WorkoutService implements WorkoutServiceInterface
{
	private final AppDatabase appDatabase;

	public WorkoutService(Context context)
	{
		appDatabase = AppDatabase.getAppDatabase(context);
	}

	public Single<List<Workout>> getWorkouts(int exerciseId)
	{
		return appDatabase.workoutDao().getWorkout(exerciseId)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> createWorkout(Workout workout)
	{
		return Single.fromObservable(observer -> {
			appDatabase.workoutDao().insertWorkout(workout);
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> deleteWorkout(Workout workout)
	{
		return Single.fromObservable(observer -> {
			appDatabase.workoutDao().deleteWorkout(workout);
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> updateMultipleWorkouts(List<Workout> workouts)
	{
		return Single.fromObservable(observer -> {
			appDatabase.workoutDao().updateMultipleWorkouts(workouts);
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}
}
