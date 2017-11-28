package com.example.philippe.gymtools.Activities.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutPlanAdapter extends RecyclerView.Adapter<WorkoutPlanAdapter.ViewHolder>
{
	private List<TrainingPlan> trainingPlans;

	public WorkoutPlanAdapter(List<TrainingPlan> trainingPlans)
	{
		this.trainingPlans = trainingPlans;
	}

	@Override
	public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
	{
		View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_workout_plan, null);
		view.setLayoutParams(new RecyclerView.LayoutParams(RecyclerView.LayoutParams.MATCH_PARENT, RecyclerView.LayoutParams.WRAP_CONTENT));
		return new ViewHolder(view);
	}

	@Override
	public void onBindViewHolder(WorkoutPlanAdapter.ViewHolder holder, int position)
	{
		holder.planName.setText(trainingPlans.get(position).getName());
		if(trainingPlans.get(position).getIsDisplayedPlan())
			holder.planIsShow.setText("true");
		else
			holder.planIsShow.setText("false");
	}


	@Override
	public int getItemCount()
	{
		return trainingPlans.size();
	}

	class ViewHolder extends RecyclerView.ViewHolder
	{
		@BindView(R.id.planName)
		TextView planName;

		@BindView(R.id.planIsShow)
		TextView planIsShow;

		ViewHolder(View itemView)
		{
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}
}