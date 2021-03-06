package com.example.philippe.gymtools.Activities.Adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.philippe.gymtools.Fragments.ExerciseDetailsDialogFragment;
import com.example.philippe.gymtools.Objects.Workout;
import com.example.philippe.gymtools.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ExerciseDetailsAdapter extends RecyclerView.Adapter<ExerciseDetailsAdapter.ViewHolder>
{
	private Context context;

	private List<Workout> workouts;

	private List<Workout> updatedWorkouts = new ArrayList<>();

	public ExerciseDetailsAdapter(Context context, List<Workout> workouts)
	{
		this.context = context;
		this.workouts = workouts;
	}

	public interface onButtonClickFunction
	{
		void onListItemDeleteButtonClick(Workout workout);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_exercise_details, null);
		view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
		return new ExerciseDetailsAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		ExerciseDetailsAdapter.onButtonClickFunction buttonClickListener = (ExerciseDetailsAdapter.onButtonClickFunction) new ExerciseDetailsDialogFragment();

		holder.workoutName.setText(workouts.get(position).getName());

		holder.workoutId.setText(String.valueOf(workouts.get(position).getId()));

		holder.workoutWeight.setText(String.valueOf(workouts.get(position).getWeight()));

		holder.workoutWeight.setOnFocusChangeListener((view, b) ->
		{
			if(!b)
			{
				Workout w = workouts.get(position);
				w.setWeight(Double.parseDouble(holder.workoutWeight.getText().toString()));
				updatedWorkouts.add(w);
			}
		});

		holder.deleteWorkoutButton.setOnClickListener(v -> buttonClickListener.onListItemDeleteButtonClick(workouts.get(position)));
	}

	@Override
	public int getItemCount()
	{
		return workouts.size();
	}

	public List<Workout> getUpdatedWorkouts()
	{
		return updatedWorkouts;
	}

	public void addNewWorkout(Workout workout)
	{
		workouts.add(workout);
		this.notifyDataSetChanged();
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.workoutName)
		TextView workoutName;

		@BindView(R.id.workoutId)
		TextView workoutId;

		@BindView(R.id.workoutWeight)
		EditText workoutWeight;

		@BindView(R.id.deleteWorkoutButton)
		Button deleteWorkoutButton;

		public ViewHolder(View itemView)
		{
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}
