package com.example.philippe.gymtools.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.philippe.gymtools.Activities.Adapters.WorkoutPlanAdapter;
import com.example.philippe.gymtools.Activities.ViewInterface.WorkoutPlanView;
import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.Module.AppDatabase;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.PresenterInterface.WorkoutPlanInterface;
import com.example.philippe.gymtools.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutPlanActivity extends AppCompatActivity implements
		WorkoutPlanView,
		WorkoutPlanAdapter.onButtonClickFunction,
		WorkoutPlanAdapter.OnItemClickListener
{
	@BindView(R.id.selectedPlanList)
	RecyclerView displayedPlans;

	@Inject
	WorkoutPlanInterface workoutPlanPresenter;

	private WorkoutPlanAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout_plan);
		ButterKnife.bind(this);

		((GymToolsApplication)getApplication())
				.getAppComponent()
				.inject(this);

		displayedPlans.setLayoutManager(new LinearLayoutManager(this));

		workoutPlanPresenter.setView(this);
		workoutPlanPresenter.setDatabase(this);
		workoutPlanPresenter.getDisplayedTrainingPlans();
	}

	@Override
	protected void onResume()
	{
		super.onResume();
		workoutPlanPresenter.getDisplayedTrainingPlans();
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		AppDatabase.destroyInstance();
	}

	public void setSelectedPlansInView(List<TrainingPlan> trainingPlans)
	{
		adapter = new WorkoutPlanAdapter(WorkoutPlanActivity.this, trainingPlans);
		displayedPlans.setAdapter(adapter);
	}

	@Override
	public void onListItemSwitchButtonClick(TrainingPlan trainingPlan)
	{
		Intent intent = TrainingPlansActivity.createIntent(this, trainingPlan);
		this.startActivity(intent);
	}

	@Override
	public void onListItemDeleteButtonClick(TrainingPlan trainingPlan)
	{
		workoutPlanPresenter.deleteTrainingPlan(trainingPlan);
		displayedPlans.setAdapter(null);
		workoutPlanPresenter.getDisplayedTrainingPlans();
	}

	@Override
	public void onListItemClick(TrainingPlan trainingPlan)
	{
		Intent intent = PlanDetailsActivity.createIntent(this, trainingPlan);
		this.startActivity(intent);
	}

	public static Intent createIntent(Context context)
	{
		Intent intent = new Intent(context, WorkoutPlanActivity.class);
		return intent;
	}
}
