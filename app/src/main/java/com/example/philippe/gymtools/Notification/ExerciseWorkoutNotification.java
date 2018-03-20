package com.example.philippe.gymtools.Notification;

import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.philippe.gymtools.Objects.Workout;
import com.example.philippe.gymtools.Presenter.NotificationPresenter;
import com.example.philippe.gymtools.Presenter.PresenterInterface.NotificationInterface;
import com.example.philippe.gymtools.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ExerciseWorkoutNotification extends IntentService
{
	private Context context;

	private ArrayList<Workout> workouts;

	private final double WeightValueChange = 2.5;

	public ExerciseWorkoutNotification()
	{
		super("ExerciseWorkoutNotification");
	}

	public ExerciseWorkoutNotification(String name, Context context)
	{
		super(name);
		this.context = context;
		workouts = new ArrayList<>();
	}

	public void setWorkouts(List<Workout> workouts)
	{
		this.workouts.addAll(workouts);
	}

	public void createNotification(String trainingPlanName, HashMap<Integer, String> exercisesName)
	{
		NotificationCompat.Builder builder = buildNotification(context, trainingPlanName, exercisesName, workouts, 0);

		NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(789450);
		notificationManager.notify(0, builder.build());
	}

	public void createNextNotification(String trainingPlanName, HashMap<Integer, String> exercisesName, ArrayList<Workout> workouts, int workoutPosition, @Nullable Integer operator)
	{
		Context context = getApplicationContext();

		Workout currentWorkout = workouts.get(workoutPosition);

		double weight = currentWorkout.getWeight();

		if(operator != null)
		{
			if (operator == 1)
			{
				currentWorkout.setWeight(weight + WeightValueChange);
			}
			else if (operator == 2 && currentWorkout.getWeight() > 0)
			{
				currentWorkout.setWeight(weight - WeightValueChange);
			}
		}

		NotificationCompat.Builder builder = buildNotification(context, trainingPlanName, exercisesName, workouts, workoutPosition);

		NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(workoutPosition, builder.build());
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent)
	{
		if(intent != null && !intent.getAction().isEmpty())
		{
			if(intent.getAction().equals("complete"))
			{
				Single.just("complete")
						.subscribeOn(Schedulers.io())
						.observeOn(AndroidSchedulers.mainThread())
						.subscribeWith(new DisposableSingleObserver<String>()
				{
					@Override
					public void onSuccess(String s)
					{
						HashMap<Integer, String> exercisesName = (HashMap<Integer, String>) intent.getSerializableExtra("exercisesName");
						ArrayList<Workout> workouts = (ArrayList<Workout>) intent.getSerializableExtra("workouts");
						int workoutPosition = intent.getIntExtra("workoutPosition", 125523);

						NotificationManager notificationManager = (android.app.NotificationManager) getApplicationContext().getSystemService(NOTIFICATION_SERVICE);
						notificationManager.cancel(workoutPosition);

						if (workoutPosition < workouts.size() - 1)
						{
							createNextNotification(intent.getStringExtra("planName"), exercisesName, workouts, workoutPosition + 1, null);
						}
						else
						{
							NotificationInterface notificationPresenter = new NotificationPresenter();

							notificationPresenter.setExerciseWorkoutNotification(getApplicationContext());
							notificationPresenter.updateNotificationWorkouts(workouts);

							notificationPresenter.setTrainingPlanNotification(getApplicationContext());
							notificationPresenter.getNotificationDisplayedTrainingPlans();
						}
					}

					@Override
					public void onError(Throwable e)
					{
						e.printStackTrace();
						dispose();
					}
				});
			}
			else if(intent.getAction().equals("upWeight") || intent.getAction().equals("downWeight"))
			{
				HashMap<Integer, String> exercisesName = (HashMap<Integer, String>) intent.getSerializableExtra("exercisesName");
				ArrayList<Workout> workouts = (ArrayList<Workout>) intent.getSerializableExtra("workouts");
				int workoutPosition = intent.getIntExtra("workoutPosition", 125523);
				int operator = intent.getIntExtra("operator", 3);

				createNextNotification(intent.getStringExtra("planName"), exercisesName, workouts, workoutPosition, operator);
			}
		}
	}

	@NonNull
	private NotificationCompat.Builder buildNotification(Context context, String trainingPlanName, HashMap<Integer, String> exercisesName, ArrayList<Workout> workouts, int workoutPosition)
	{
		Workout currentWorkout = workouts.get(workoutPosition);

		RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.remote_view_exercise_workout);

		expandedView.setTextViewText(R.id.NotificationExerciseName, exercisesName.get(currentWorkout.getExerciseId()));
		expandedView.setTextViewText(R.id.NotificationWorkoutName, currentWorkout.getName());
		expandedView.setTextViewText(R.id.NotificationWeight, String.valueOf(currentWorkout.getWeight()));

		Intent completeWorkoutIntent = new Intent(context, ExerciseWorkoutNotification.class);
		completeWorkoutIntent.setAction("complete");
		completeWorkoutIntent.putExtra("planName", trainingPlanName);
		completeWorkoutIntent.putExtra("exercisesName", exercisesName);
		completeWorkoutIntent.putExtra("workouts", workouts);
		completeWorkoutIntent.putExtra("workoutPosition", workoutPosition);

		Intent addWeightIntent = new Intent(context, ExerciseWorkoutNotification.class);
		addWeightIntent.setAction("upWeight");
		addWeightIntent.putExtra("operator", 1);
		addWeightIntent.putExtra("planName", trainingPlanName);
		addWeightIntent.putExtra("exercisesName", exercisesName);
		addWeightIntent.putExtra("workouts", workouts);
		addWeightIntent.putExtra("workoutPosition", workoutPosition);

		Intent downWeightIntent = new Intent(context, ExerciseWorkoutNotification.class);
		downWeightIntent.setAction("downWeight");
		downWeightIntent.putExtra("operator", 2);
		downWeightIntent.putExtra("planName", trainingPlanName);
		downWeightIntent.putExtra("exercisesName", exercisesName);
		downWeightIntent.putExtra("workouts", workouts);
		downWeightIntent.putExtra("workoutPosition", workoutPosition);

		expandedView.setOnClickPendingIntent(R.id.CompleteWorkoutButton, PendingIntent.getService(context, 0, completeWorkoutIntent, PendingIntent.FLAG_UPDATE_CURRENT));
		expandedView.setOnClickPendingIntent(R.id.UpWeightButton, PendingIntent.getService(context, 1, addWeightIntent, PendingIntent.FLAG_UPDATE_CURRENT));
		expandedView.setOnClickPendingIntent(R.id.DownWeightButton, PendingIntent.getService(context, 2, downWeightIntent, PendingIntent.FLAG_UPDATE_CURRENT));

		NotificationCompat.Builder builder =
				new NotificationCompat.Builder(context)
						.setSmallIcon(R.mipmap.ic_launcher)
						.setContentTitle("Slide the notification down to register your set")
						.setContentText("Slide down")
						.setStyle(new NotificationCompat.BigTextStyle());

		builder.setCustomBigContentView(expandedView);
		return builder;
	}
}
