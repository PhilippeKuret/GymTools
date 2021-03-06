package com.example.philippe.gymtools.Module;

import android.content.Context;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class TrainingPlanService implements TrainingPlanServiceInterface
{
	private final AppDatabase appDatabase;

	public TrainingPlanService(Context context)
	{
		appDatabase = AppDatabase.getAppDatabase(context);
	}

	public Single<List<TrainingPlan>> getDisplayedTrainingPlans()
	{
		return appDatabase.trainingPlanDao().getTrainingPlansDisplayedOption(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<List<TrainingPlan>> getNotDisplayedTrainingPlans()
	{
		return appDatabase.trainingPlanDao().getTrainingPlansDisplayedOption(false)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> createTrainingPlan(TrainingPlan trainingPlan)
	{
		return Single.fromObservable(observer -> appDatabase.trainingPlanDao().insertTrainingPlan(trainingPlan))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> deleteTrainingPlan(TrainingPlan trainingPlan)
	{
		return Single.fromObservable(observer -> appDatabase.trainingPlanDao().deleteTrainingPlan(trainingPlan))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> updateMultipleTrainingPlans(TrainingPlan... trainingPlans)
	{
		return Single.fromObservable(observer -> appDatabase.trainingPlanDao().updateMultipleTrainingPlans(trainingPlans))
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}
}
