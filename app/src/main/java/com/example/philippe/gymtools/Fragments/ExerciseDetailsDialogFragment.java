package com.example.philippe.gymtools.Fragments;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcelable;
import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.philippe.gymtools.Activities.Adapters.ExerciseDetailsAdapter;
import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.Fragments.FragmentInterface.ExerciseDetailsFragmentView;
import com.example.philippe.gymtools.Objects.Exercise;
import com.example.philippe.gymtools.Objects.Workout;
import com.example.philippe.gymtools.Presenter.PresenterInterface.ExerciseDetailsInterface;
import com.example.philippe.gymtools.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDetailsDialogFragment extends DialogFragment implements ExerciseDetailsFragmentView, ExerciseDetailsAdapter.onButtonClickFunction
{
	@BindView(R.id.selectedExerciseName)
	TextView selectedExerciseName;

	@BindView(R.id.workoutList)
	RecyclerView workoutsRecyclerView;

	@BindView(R.id.newWorkoutButton)
	Button newWorkoutButton;

	private Exercise selectedExercise;

	private ExerciseDetailsAdapter adapter;

	@Inject
	ExerciseDetailsInterface exerciseDetailsPresenter;

	@Override
	public void onAttach(Context context)
	{
		super.onAttach(context);

		((GymToolsApplication)context.getApplicationContext())
				.getAppComponent()
				.inject(this);
	}

	@Override
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);

		exerciseDetailsPresenter.setDatabase(getContext());
		exerciseDetailsPresenter.setView(this);

		Parcelable parcelable = getArguments().getParcelable("exercise");

		if(parcelable != null && parcelable.getClass() == Exercise.class)
		{
			selectedExercise = getArguments().getParcelable("exercise");
		}

		exerciseDetailsPresenter.getWorkouts(selectedExercise.getId());
	}

	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_exercise_details, container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);

		workoutsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

		selectedExerciseName.setText(selectedExercise.getName());

		newWorkoutButton.setOnClickListener(view1 -> {
			Workout newWorkout = new Workout(0, "Set " + (adapter.getItemCount() + 1), 0.0, selectedExercise.getId());
			exerciseDetailsPresenter.addWorkout(newWorkout);
			adapter.addNewWorkout(newWorkout);
		});
	}

	@Override
	public void createBundle(Exercise exercise)
	{
		Bundle bundle = new Bundle();
		bundle.putParcelable("exercise", exercise);
		this.setArguments(bundle);
	}

	@Override
	public void setWorkoutsInView(List<Workout> workouts)
	{
		adapter = new ExerciseDetailsAdapter(getContext(), workouts);
		workoutsRecyclerView.setAdapter(adapter);
	}

	@Override
	public void onDismiss(DialogInterface dialog)
	{
		super.onDismiss(dialog);
		List<Workout> update = adapter.getUpdatedWorkouts();
		exerciseDetailsPresenter.updateMultipleWorkouts(update);
	}

	@Override
	public void onListItemDeleteButtonClick(Workout workout)
	{
		exerciseDetailsPresenter.deleteWorkout(workout);
		workoutsRecyclerView.setAdapter(null);
		exerciseDetailsPresenter.getWorkouts(selectedExercise.getId());
	}
}
