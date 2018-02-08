package com.example.philippe.gymtools.Presenter;


import android.annotation.SuppressLint;
import android.content.Context;

import com.example.philippe.gymtools.Module.ExerciseService;
import com.example.philippe.gymtools.Module.ExerciseServiceInterface;
import com.example.philippe.gymtools.Module.TrainingPlanService;
import com.example.philippe.gymtools.Module.TrainingPlanServiceInterface;
import com.example.philippe.gymtools.Module.WorkoutService;
import com.example.philippe.gymtools.Module.WorkoutServiceInterface;
import com.example.philippe.gymtools.Notification.ExerciseWorkoutNotification;
import com.example.philippe.gymtools.Notification.TrainingPlanNotification;
import com.example.philippe.gymtools.Objects.Exercise;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Objects.Workout;
import com.example.philippe.gymtools.Presenter.PresenterInterface.NotificationInterface;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;

public class NotificationPresenter implements NotificationInterface
{
	private TrainingPlanServiceInterface trainingPlanService;

	private ExerciseServiceInterface exerciseService;

	private WorkoutServiceInterface workoutService;

	private TrainingPlanNotification trainingPlanNotification;

	private ExerciseWorkoutNotification exerciseWorkoutNotification;

	public void setTrainingPlanNotification(Context context)
	{
		trainingPlanService = new TrainingPlanService(context);
		trainingPlanNotification = new TrainingPlanNotification("trainingPlanNotification", context);
	}

	public void setExerciseWorkoutNotification(Context context)
	{
		exerciseService = new ExerciseService(context);
		workoutService = new WorkoutService(context);
		exerciseWorkoutNotification = new ExerciseWorkoutNotification("exerciseWorkoutNotification", context);
	}

	public void getNotificationDisplayedTrainingPlans()
	{
		trainingPlanService.getDisplayedTrainingPlans().subscribeWith(new DisposableSingleObserver<List<TrainingPlan>>()
		{

			@Override
			public void onSuccess(List<TrainingPlan> trainingPlans)
			{
				trainingPlanNotification.createNotification(trainingPlans);
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

	public void getNotificationExerciseWorkouts(TrainingPlan trainingPlan)
	{
		exerciseService.getExercises(trainingPlan.getId()).subscribeWith(new DisposableSingleObserver<List<Exercise>>()
		{
			@Override
			public void onSuccess(List<Exercise> exercises)
			{
				@SuppressLint("UseSparseArrays")
				HashMap<Integer, String> map = new HashMap<>(exercises.size());

				List<Single<List<Workout>>> singleList = new ArrayList<>();

				for(Exercise exercise : exercises)
				{
					map.put(exercise.getId(), exercise.getName());
					singleList.add(workoutService.getWorkouts(exercise.getId()));
				}


				getNotificationWorkouts(singleList, trainingPlan, map);
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

	public void updateNotificationWorkouts(List<Workout> workouts)
	{
		workoutService.updateMultipleWorkouts(workouts)
				.subscribe();

	}

	private void getNotificationWorkouts(List<Single<List<Workout>>> exercise, TrainingPlan trainingPlan, HashMap<Integer, String> map)
	{
		Single.concat(exercise)
				.doOnComplete(() -> exerciseWorkoutNotification.createNotification(trainingPlan.getName(), map))
				.doOnNext(workouts -> exerciseWorkoutNotification.setWorkouts(workouts))
				.subscribe();

	}
}
