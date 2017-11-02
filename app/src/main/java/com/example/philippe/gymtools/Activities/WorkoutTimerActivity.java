package com.example.philippe.gymtools.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.philippe.gymtools.Fragments.CustomTimeDialogFragment;
import com.example.philippe.gymtools.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutTimerActivity extends AppCompatActivity implements CustomTimeDialogFragment.OnTimeSelectedListener
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

	private long RESTTIME = 60;

	boolean isOnLoop = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout_timer);
		ButterKnife.bind(this);

		final CountDownTimer countDownTimer = new CountDownTimer(TimeUnit.SECONDS.toMillis(RESTTIME), 1000)
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

				//Vibrate 3 time for 1 second with 1 second interval
				long[] pattern = {0, 1000, 1000, 1000, 1000, 1000};

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

		restTimer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				FragmentManager fm = getSupportFragmentManager();
				CustomTimeDialogFragment customTimeDialogFragment = CustomTimeDialogFragment.newInstance();
				customTimeDialogFragment.show(fm, "fragment_custom_timer");
			}
		});
	}

	@Override
	public void onTimeSelected(String time)
	{
		restTimer.setText(time);
		SimpleDateFormat dateFormat = new SimpleDateFormat("mm:ss");
		try {
			Date date = dateFormat.parse(time);
			//TODO give value of time selected to Timer
			//RESTTIME = date.getTime();
			//Toast.makeText(this, String.valueOf(RESTTIME), Toast.LENGTH_LONG).show();
		} catch (ParseException e) {
		}
	}
}
