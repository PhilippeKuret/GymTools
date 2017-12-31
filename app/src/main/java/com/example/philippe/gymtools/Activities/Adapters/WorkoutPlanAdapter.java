package com.example.philippe.gymtools.Activities.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutPlanAdapter extends RecyclerView.Adapter<WorkoutPlanAdapter.ViewHolder>
{
	private Context context;

	private List<TrainingPlan> trainingPlans;

	public WorkoutPlanAdapter(Context context, List<TrainingPlan> trainingPlans)
	{
		this.context = context;
		this.trainingPlans = trainingPlans;
	}

	public interface onButtonClickFunction
	{
		void onListItemSwitchButtonClick(TrainingPlan trainingPlan);
		void onListItemDeleteButtonClick(TrainingPlan trainingPlan);
	}

	public interface OnItemClickListener
	{
		void onListItemClick(TrainingPlan trainingPlan);
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

		OnItemClickListener itemClickListener = (OnItemClickListener) context;

		holder.setOnClickListener(trainingPlans.get(position), itemClickListener);

		holder.planName.setText(trainingPlans.get(position).getName());

		holder.planId.setText(String.valueOf(trainingPlans.get(position).getId()));

		if(trainingPlans.get(position).getIsDisplayedPlan())
			holder.planIsShow.setText("true");
		else
			holder.planIsShow.setText("false");

		holder.planListNavigationButton.setOnClickListener(v ->
		{
			onButtonClickFunction buttonClickListener = (onButtonClickFunction) context;
			buttonClickListener.onListItemSwitchButtonClick(trainingPlans.get(position));
		});

		holder.deletePlanButton.setOnClickListener(v -> {
			onButtonClickFunction buttonClickListener = (onButtonClickFunction) context;
			buttonClickListener.onListItemDeleteButtonClick(trainingPlans.get(position));
		});
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

		@BindView(R.id.planId)
		TextView planId;

		@BindView(R.id.planListNavigationButton)
		Button planListNavigationButton;

		@BindView(R.id.deletePlanButton)
		Button deletePlanButton;

		ViewHolder(View itemView)
		{
			super(itemView);
			ButterKnife.bind(this, itemView);
		}

		public void setOnClickListener(TrainingPlan trainingPlan, OnItemClickListener listener)
		{
			itemView.setOnClickListener(v -> listener.onListItemClick(trainingPlan));
		}
	}
}
