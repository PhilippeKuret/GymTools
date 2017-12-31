package com.example.philippe.gymtools.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.philippe.gymtools.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateExerciseDialogFragment extends DialogFragment
{
	@BindView(R.id.newExerciseName)
	EditText newExerciseName;

	@BindView(R.id.acceptNewExerciseButton)
	Button newExerciseButton;

	public interface OnExerciseCreatedListener
	{
		void OnExercisePlanCreated(String name);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_create_exercise, container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);

		newExerciseButton.setOnClickListener(v ->
		{
			CreateExerciseDialogFragment.OnExerciseCreatedListener listener = (CreateExerciseDialogFragment.OnExerciseCreatedListener) getActivity();
			listener.OnExercisePlanCreated(newExerciseName.getText().toString());
			dismiss();
		});
	}
}
