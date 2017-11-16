package com.example.philippe.gymtools.Module;

import android.content.Context;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DatabaseService
{
	private final AppDatabase appDatabase;

	public DatabaseService(Context context)
	{
		appDatabase = AppDatabase.getAppDatabase(context);
	}

	public Single<List<TrainingPlan>> getTrainingPlans()
	{
		return appDatabase.trainingPlanDao().getTrainingPlans()
				.flattenAsObservable(trainingPlans -> trainingPlans)
				.filter(trainingPlan -> trainingPlan.getIsDisplayedPlan())
				.toList()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}
}
