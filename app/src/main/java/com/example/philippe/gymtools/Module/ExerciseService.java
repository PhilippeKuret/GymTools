package com.example.philippe.gymtools.Module;

import android.content.Context;

import com.example.philippe.gymtools.Objects.Exercise;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ExerciseService implements ExerciseServiceInterface
{
	private final AppDatabase appDatabase;

	public ExerciseService(Context context)
	{
		appDatabase = AppDatabase.getAppDatabase(context);
	}

	public Single<List<Exercise>> getExercises(int planId)
	{
		return appDatabase.exerciseDao().getExercises(planId)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> createExercise(Exercise exercise)
	{
		return Single.fromObservable(observer -> {
			appDatabase.exerciseDao().insertExercise(exercise);
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> deleteExercise(Exercise exercise)
	{
		return Single.fromObservable(observer -> {
			appDatabase.exerciseDao().deleteExervice(exercise);
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}
}
