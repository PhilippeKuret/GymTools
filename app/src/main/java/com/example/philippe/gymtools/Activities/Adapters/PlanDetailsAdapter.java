package com.example.philippe.gymtools.Activities.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.philippe.gymtools.Objects.Exercise;
import com.example.philippe.gymtools.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanDetailsAdapter extends RecyclerView.Adapter<PlanDetailsAdapter.ViewHolder>
{
	private Context context;

	private List<Exercise> exercises;

	public PlanDetailsAdapter(Context context, List<Exercise> exercises)
	{
		this.context = context;
		this.exercises = exercises;
	}

	public interface onButtonClickFunction
	{
		void onListItemDeleteButtonClick(Exercise exercise);
	}

	public interface OnItemClickListener
	{
		void onListItemClick(Exercise exercise);
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_plan_details, null);
		view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
		return new PlanDetailsAdapter.ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(ViewHolder holder, int position)
	{
		PlanDetailsAdapter.OnItemClickListener itemClickListener = (PlanDetailsAdapter.OnItemClickListener) context;
		PlanDetailsAdapter.onButtonClickFunction buttonClickListener = (PlanDetailsAdapter.onButtonClickFunction) context;

		holder.setOnClickListener(exercises.get(position), itemClickListener);

		holder.exerciseName.setText(exercises.get(position).getName());

		holder.exerciseId.setText(String.valueOf(exercises.get(position).getId()));

		holder.deleteExerciseButton.setOnClickListener(v ->
				buttonClickListener.onListItemDeleteButtonClick(exercises.get(position)));
	}

	@Override
	public int getItemCount()
	{
		return exercises.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.exerciseName)
		TextView exerciseName;

		@BindView(R.id.exerciseId)
		TextView exerciseId;

		@BindView(R.id.deleteExerciseButton)
		Button deleteExerciseButton;

		ViewHolder(View itemView)
		{
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void setOnClickListener(Exercise exercise, PlanDetailsAdapter.OnItemClickListener listener)
		{
			itemView.setOnClickListener(v -> listener.onListItemClick(exercise));
		}
	}
}
