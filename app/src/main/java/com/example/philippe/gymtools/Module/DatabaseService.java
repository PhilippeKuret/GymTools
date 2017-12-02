package com.example.philippe.gymtools.Module;

import android.content.Context;

import com.example.philippe.gymtools.Objects.TrainingPlan;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;


public class DatabaseService implements DatabaseServiceInterface
{
	private final AppDatabase appDatabase;

	public DatabaseService(Context context)
	{
		appDatabase = AppDatabase.getAppDatabase(context);
	}

	public Single<List<TrainingPlan>> getDisplayedTrainingPlans()
	{
		return appDatabase.trainingPlanDao().getDisplayedTrainingPlans(true)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<List<TrainingPlan>> getTrainingPlans()
	{
		return appDatabase.trainingPlanDao().getTrainingPlans()
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> createTrainingPlan(TrainingPlan trainingPlan)
	{
		return Single.fromObservable(observer ->
				appDatabase.trainingPlanDao().insertTrainingPlan(trainingPlan)
		)
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}

	public Single<Object> deleteTrainingPlan(TrainingPlan trainingPlan)
	{
		return Single.fromObservable(observer -> {
			appDatabase.trainingPlanDao().deleteTrainingPlan(trainingPlan);
		})
				.subscribeOn(Schedulers.io())
				.observeOn(AndroidSchedulers.mainThread());
	}
}
