package com.example.philippe.gymtools.Module;

import android.content.Context;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import org.reactivestreams.Subscription;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DatabaseService
{
	private final AppDatabase appDatabase;

	public DatabaseService(Context context)
	{
		appDatabase = AppDatabase.getAppDatabase(context);
	}

	public Subscription getTrainingPlans()
	{
//		return appDatabase.trainingPlanDao().getTrainingPlans()
//				.subscribeOn(Schedulers.io())
//				.observeOn(AndroidSchedulers.mainThread())
//				.subscribe(x -> {
//
//				});
	}
}
