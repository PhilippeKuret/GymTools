package com.example.philippe.gymtools.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.philippe.gymtools.Activities.Adapters.WorkoutPlanAdapter;
import com.example.philippe.gymtools.Activities.ViewInterface.WorkoutPlanView;
import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.Fragments.CreateTrainingPlanDialogFragment;
import com.example.philippe.gymtools.Module.AppDatabase;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.PresenterInterface.WorkoutPlanInterface;
import com.example.philippe.gymtools.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutPlanActivity extends AppCompatActivity implements WorkoutPlanView
{
	@BindView(R.id.selectedPlanList)
	RecyclerView displayedPlans;

	@Inject
	WorkoutPlanInterface workoutPlanPresenter;

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
	protected void onDestroy()
	{
		super.onDestroy();
		AppDatabase.destroyInstance();
	}

	public void setSelectedPlansInView(List<TrainingPlan> trainingPlans)
	{
		displayedPlans.setAdapter(new WorkoutPlanAdapter(WorkoutPlanActivity.this, trainingPlans));
	}
}
