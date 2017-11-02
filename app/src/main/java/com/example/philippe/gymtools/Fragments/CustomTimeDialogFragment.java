package com.example.philippe.gymtools.Fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.example.philippe.gymtools.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomTimeDialogFragment extends DialogFragment
{

	@BindView(R.id.minuteNumberPicker)
	NumberPicker minuteNumberPicker;

	@BindView(R.id.secondNumberPicker)
	NumberPicker secondNumberPicker;

	@BindView(R.id.selectedTime)
	TextView selectedTime;

	@BindView(R.id.acceptTime)
	Button acceptTime;

	public CustomTimeDialogFragment() {}

	public static CustomTimeDialogFragment newInstance()
	{
		CustomTimeDialogFragment frag = new CustomTimeDialogFragment();
		Bundle args = new Bundle();
		frag.setArguments(args);
		return frag;
	}

	public static interface OnTimeSelectedListener {
		public abstract void onTimeSelected(String time);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_custom_timer_dialog, container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		ButterKnife.bind(this, view);

		minuteNumberPicker.setMaxValue(59);
		secondNumberPicker.setMaxValue(59);

		minuteNumberPicker.setMinValue(0);
		secondNumberPicker.setMinValue(0);

		selectedTime.setText("00:00");

		minuteNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
		{
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal)
			{
				selectedTime.setText(newVal + ":" + secondNumberPicker.getValue());
			}
		});

		secondNumberPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener()
		{
			@Override
			public void onValueChange(NumberPicker picker, int oldVal, int newVal)
			{
				selectedTime.setText(minuteNumberPicker.getValue() + ":" + newVal);
			}
		});

		acceptTime.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				OnTimeSelectedListener listener = (OnTimeSelectedListener) getActivity();
				listener.onTimeSelected(minuteNumberPicker.getValue() + ":" + secondNumberPicker.getValue());
				dismiss();
			}
		});
	}
}
