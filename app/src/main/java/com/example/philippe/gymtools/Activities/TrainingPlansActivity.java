package com.example.philippe.gymtools.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;

import com.example.philippe.gymtools.Activities.Adapters.WorkoutPlanAdapter;
import com.example.philippe.gymtools.Activities.ViewInterface.TrainingPlansView;
import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.Fragments.CreateTrainingPlanDialogFragment;
import com.example.philippe.gymtools.Module.AppDatabase;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.PresenterInterface.TrainingPlansInterface;
import com.example.philippe.gymtools.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TrainingPlansActivity extends AppCompatActivity implements TrainingPlansView, CreateTrainingPlanDialogFragment.OnPlanCreatedListener, WorkoutPlanAdapter.onButtonClickFunction
{

	@BindView(R.id.completePlanList)
	RecyclerView plans;

	@BindView(R.id.newPlanButton)
	Button newPlanButton;

	@Inject
	TrainingPlansInterface trainingPlansPresenter;

	private WorkoutPlanAdapter adapter;

	private TrainingPlan selectedTrainingPlan;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training_plans);
		ButterKnife.bind(this);

		((GymToolsApplication)getApplication())
				.getAppComponent()
				.inject(this);

		Bundle data = getIntent().getExtras();
		selectedTrainingPlan = data.getParcelable("plan");

		plans.setLayoutManager(new LinearLayoutManager(this));

		trainingPlansPresenter.setView(this);
		trainingPlansPresenter.setDatabase(this);
		trainingPlansPresenter.getTrainingPlans();

		newPlanButton.setOnClickListener(v -> {
			FragmentManager fm = getSupportFragmentManager();
			CreateTrainingPlanDialogFragment createTrainingPlanDialogFragment = new CreateTrainingPlanDialogFragment();
			createTrainingPlanDialogFragment.show(fm, "create_training_plan");
		});
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		AppDatabase.destroyInstance();
	}

	public void setSelectedPlansInView(List<TrainingPlan> trainingPlans)
	{
		adapter = new WorkoutPlanAdapter(TrainingPlansActivity.this, trainingPlans);
		plans.setAdapter(adapter);
	}

	@Override
	public void OnPlanCreated(String name, Boolean isShown)
	{
		trainingPlansPresenter.createTrainingPlan(name, isShown);
		plans.setAdapter(null);
		trainingPlansPresenter.getTrainingPlans();
	}

	//UPDATE ON SWITCH
	@Override
	public void onListItemButtonClick(TrainingPlan trainingPlan)
	{
		trainingPlan.setIsDisplayedPlan(true);
		selectedTrainingPlan.setIsDisplayedPlan(false);

		trainingPlansPresenter.updateMultipleTrainingPlans(trainingPlan, selectedTrainingPlan);
		finish();
	}

	@Override
	public void deleteListItemButtonClick(TrainingPlan trainingPlan)
	{
		trainingPlansPresenter.deleteTrainingPlan(trainingPlan);
		plans.setAdapter(null);
		trainingPlansPresenter.getTrainingPlans();
	}

	public static Intent createIntent(Context context, TrainingPlan plan)
	{
		Intent intent = new Intent(context, TrainingPlansActivity.class);
		intent.putExtra("plan", plan);
		return intent;
	}
}
