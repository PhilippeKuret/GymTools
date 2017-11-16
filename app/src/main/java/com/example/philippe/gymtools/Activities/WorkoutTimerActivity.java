package com.example.philippe.gymtools.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.philippe.gymtools.Fragments.CustomTimeDialogFragment;
import com.example.philippe.gymtools.R;
import com.example.philippe.gymtools.Utils.MathTools;

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

	final private long mMinuteInMilli = 60000;

	final private long mSecondInMilli = 1000;

	private long mRestTime = 60000;

	private CountDownTimer mCountDownTimer;

	private boolean mIsOnLoop = false;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout_timer);
		ButterKnife.bind(this);

		SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
		if(sharedPref.contains("userTime"))
		{
			mRestTime = sharedPref.getLong("userTime", 60000);
		}

		restTimer.setText(MathTools.MilliToMinuteTimeInString(mRestTime));

		startTimer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mCountDownTimer = getCountDownTimer();
				restTimer.setClickable(false);
				mCountDownTimer.start();
			}
		});

		stopTimer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				mCountDownTimer.cancel();
				restTimer.setClickable(true);
				restTimer.setText(MathTools.MilliToMinuteTimeInString(mRestTime));
			}
		});

		loopTimer.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				if(loopTimer.isChecked())
				{
					mIsOnLoop = true;
				}
				else
				{
					mIsOnLoop = false;
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
				CustomTimeDialogFragment customTimeDialogFragment = CustomTimeDialogFragment.newInstance(mRestTime);
				customTimeDialogFragment.show(fm, "fragment_custom_timer");
			}
		});
	}

	@Override
	protected void onPause()
	{
		super.onPause();
		SharedPreferences sharedPref = this.getPreferences(Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		editor.putLong("userTime", mRestTime);
		editor.apply();
	}

	@Override
	public void onTimeSelected(String time)
	{
		restTimer.setText(time);
		mRestTime = MathTools.StringTimeMinuteToMilli(time);
	}

	@NonNull
	private CountDownTimer getCountDownTimer()
	{
		return new CountDownTimer(mRestTime, 1000)
		{
			@Override
			public void onTick(long millisUntilFinished)
			{
				long minuteLeft = 0;
				if(millisUntilFinished >= mMinuteInMilli)
				{
					minuteLeft = millisUntilFinished/mMinuteInMilli;
					millisUntilFinished = millisUntilFinished - (minuteLeft*mMinuteInMilli);
				}

				long secondLeft = millisUntilFinished/mSecondInMilli;

				if(secondLeft < 10)
				{
					restTimer.setText(minuteLeft + ":0" + secondLeft);
				}
				else
				{
					restTimer.setText(minuteLeft + ":" + secondLeft);
				}
			}

			@Override
			public void onFinish()
			{
				Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

				//Vibrate 3 time for 1 second with 1 second interval
				long[] pattern = {0, 1000, 1000, 1000, 1000, 1000};

				v.vibrate(pattern, -1);
				restTimer.setText(MathTools.MilliToMinuteTimeInString(mRestTime));
				restTimer.setClickable(true);
				if(mIsOnLoop)
				{
					start();
				}
			}
		};
	}
}
