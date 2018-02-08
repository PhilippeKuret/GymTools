package com.example.philippe.gymtools.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;

import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.Presenter.PresenterInterface.NotificationInterface;
import com.example.philippe.gymtools.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StartingActivity extends AppCompatActivity
{
	@BindView(R.id.notificationButton)
	Button notificationButton;

	@BindView(R.id.weightInputButton)
	Button weightInputButton;

	@Inject
	NotificationInterface notificationPresenter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_starting);

		ButterKnife.bind(this);

		((GymToolsApplication)getApplication())
				.getAppComponent()
				.inject(this);

		weightInputButton.setOnClickListener(view -> {
			Intent intent = WorkoutPlanActivity.createIntent(this);
			this.startActivity(intent);
		});

		notificationButton.setOnClickListener(view -> {
			notificationPresenter.setTrainingPlanNotification(this);
			notificationPresenter.getNotificationDisplayedTrainingPlans();
		});

	}
}
