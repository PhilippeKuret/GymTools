package com.example.philippe.gymtools.Fragments;


import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.philippe.gymtools.Objects.Exercise;

public class ExerciseDetailsDialogFragment extends DialogFragment
{
	private Exercise selectedExercise;

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		if(getArguments().getParcelable("exercise") != null)
		{
			selectedExercise=getArguments().getParcelable("exercise");
		}
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
	{
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
	}

	public void createBundle(Exercise exercise)
	{
		Bundle bundle = new Bundle();
		bundle.putParcelable("exercise", exercise);
		this.setArguments(bundle);
	}
}
