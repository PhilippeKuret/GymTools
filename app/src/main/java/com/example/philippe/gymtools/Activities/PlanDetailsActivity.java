package com.example.philippe.gymtools.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.philippe.gymtools.Activities.Adapters.WorkoutPlanAdapter;
import com.example.philippe.gymtools.Activities.ViewInterface.PlanDetailsView;
import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.Module.AppDatabase;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.PresenterInterface.PlanDetailsInterface;
import com.example.philippe.gymtools.Presenter.PresenterInterface.TrainingPlansInterface;
import com.example.philippe.gymtools.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanDetailsActivity extends AppCompatActivity implements PlanDetailsView
{
	@BindView(R.id.ExerciseList)
	RecyclerView exercises;

	@BindView(R.id.newExerciseButton)
	Button newExerciseButton;

	@Inject
	PlanDetailsInterface planDetailsPresenter;

	private WorkoutPlanAdapter adapter;

	private TrainingPlan selectedTrainingPlan;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_details);
		ButterKnife.bind(this);

		((GymToolsApplication)getApplication())
				.getAppComponent()
				.inject(this);

		exercises.setLayoutManager(new LinearLayoutManager(this));

		planDetailsPresenter.setView(this);
		planDetailsPresenter.setDatabase(this);
		planDetailsPresenter.getExercises(selectedTrainingPlan.getId());
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		AppDatabase.destroyInstance();
	}
}
