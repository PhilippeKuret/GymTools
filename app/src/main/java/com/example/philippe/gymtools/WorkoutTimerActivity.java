package com.example.philippe.gymtools;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutTimerActivity extends AppCompatActivity
{
	@BindView(R.id.restTimer)
	TextView restTimer;

	@BindView(R.id.startTimer)
	Button startTimer;

	@BindView(R.id.stopTimer)
	Button stopTimer;

	@BindView(R.id.loopTimer)
	CheckBox loopTimer;

	@BindView(R.id.WeightConvertNavigation)
	Button weightConvertActivityNavigation;

	final long RESTINSECONDS = 10;

	boolean isOnLoop = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout_timer);
		ButterKnife.bind(this);

		final CountDownTimer countDownTimer = new CountDownTimer(TimeUnit.SECONDS.toMillis(RESTINSECONDS), 1000)
		{
			@Override
			public void onTick(long millisUntilFinished)
			{
				restTimer.setText(String.valueOf(TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)));
			}

			@Override
			public void onFinish()
			{
				Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

				long[] pattern = {0, 1000, 1000, 1000, 1000, 1000};
				// Vibrate for 1 second
				v.vibrate(pattern, -1);
				restTimer.setText("1:00");
				if(isOnLoop)
				{
					start();
				}
			}
		};

		startTimer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				countDownTimer.start();
			}
		});

		stopTimer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				countDownTimer.cancel();
				restTimer.setText("1:00");
			}
		});

		loopTimer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(loopTimer.isChecked())
				{
					isOnLoop = true;
				}
				else
				{
					isOnLoop = false;
				}
			}
		});

		weightConvertActivityNavigation.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(WorkoutTimerActivity.this, MainActivity.class);
				WorkoutTimerActivity.this.startActivity(intent);
			}
		});
	}
}
