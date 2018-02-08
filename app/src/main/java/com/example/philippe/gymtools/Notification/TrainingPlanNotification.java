package com.example.philippe.gymtools.Notification;


import android.app.IntentService;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.DI.AppComponent;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.NotificationPresenter;
import com.example.philippe.gymtools.Presenter.PresenterInterface.NotificationInterface;
import com.example.philippe.gymtools.R;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class TrainingPlanNotification extends IntentService
{
	private Context context;

	public TrainingPlanNotification()
	{
		super("TrainingPlanNotification");
	}

	public TrainingPlanNotification(String name, Context context)
	{
		super(name);
		this.context = context;
	}

	public void createNotification(List<TrainingPlan> trainingPlanList)
	{
		int[] buttonList = new int[]
		{
				R.id.button1,
				R.id.button2,
				R.id.button3,
				R.id.button4,
				R.id.button5,
				R.id.button6,
				R.id.button7,
				R.id.button8
		};

		RemoteViews expandedView = new RemoteViews(context.getPackageName(), R.layout.remote_view_training_plan);

		for(int i = 0; i < trainingPlanList.size(); i++)
		{
			if(trainingPlanList.get(i) != null)
			{
				Intent intent = new Intent(context, TrainingPlanNotification.class);
				intent.setAction(trainingPlanList.get(i).getName());
				intent.putExtra("plan", trainingPlanList.get(i));

				expandedView.setOnClickPendingIntent(buttonList[i], PendingIntent.getService(context, i, intent, PendingIntent.FLAG_UPDATE_CURRENT));
				expandedView.setTextViewText(buttonList[i], String.valueOf(i));
			}
		}

		NotificationCompat.Builder builder =
				new NotificationCompat.Builder(context)
						.setSmallIcon(R.mipmap.ic_launcher)
						.setContentTitle("Slide down")
						.setContentText("Slide down to select a workout")
						.setStyle(new NotificationCompat.BigTextStyle())
						.setCustomBigContentView(expandedView);

		NotificationManager notificationManager = (android.app.NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
		notificationManager.notify(789450, builder.build());
	}

	@Override
	protected void onHandleIntent(@Nullable Intent intent)
	{
		if(!intent.getAction().isEmpty())
		{
			Single.just("plan")
					.subscribeOn(Schedulers.io())
					.observeOn(AndroidSchedulers.mainThread())
					.subscribeWith(new DisposableSingleObserver<String>()
					{
						@Override
						public void onSuccess(String s)
						{
							TrainingPlan plan = intent.getParcelableExtra(s);

							NotificationInterface notificationPresenter = new NotificationPresenter();

							notificationPresenter.setExerciseWorkoutNotification(TrainingPlanNotification.this);
							notificationPresenter.getNotificationExerciseWorkouts(plan);
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
}
