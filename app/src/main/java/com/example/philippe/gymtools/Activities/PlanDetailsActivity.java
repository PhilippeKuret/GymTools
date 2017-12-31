package com.example.philippe.gymtools.Activities;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.TextView;

import com.example.philippe.gymtools.Activities.Adapters.WorkoutPlanAdapter;
import com.example.philippe.gymtools.Activities.ViewInterface.PlanDetailsView;
import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.Fragments.CreateExerciseDialogFragment;
import com.example.philippe.gymtools.Module.AppDatabase;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.PresenterInterface.PlanDetailsInterface;
import com.example.philippe.gymtools.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanDetailsActivity extends AppCompatActivity implements PlanDetailsView, CreateExerciseDialogFragment.OnExerciseCreatedListener
{
	@BindView(R.id.ExerciseList)
	RecyclerView exercises;

	@BindView(R.id.planName)
	TextView planName;

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

		if(getIntent().hasExtra("plan"))
		{
			Bundle data = getIntent().getExtras();
			selectedTrainingPlan = data.getParcelable("plan");
		}

		planName.setText(selectedTrainingPlan.getName());

		planDetailsPresenter.setView(this);
		planDetailsPresenter.setDatabase(this);
		planDetailsPresenter.getExercises(selectedTrainingPlan.getId());

		newExerciseButton.setOnClickListener(v ->
		{
			FragmentManager fm = getSupportFragmentManager();
			CreateExerciseDialogFragment createExerciseDialogFragment = new CreateExerciseDialogFragment();
			createExerciseDialogFragment.show(fm, "create_exercise");
		});
	}

	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		AppDatabase.destroyInstance();
	}

	@Override
	public void OnExercisePlanCreated(String name)
	{

	}

	public static Intent createIntent(Context context, TrainingPlan plan)
	{
		Intent intent = new Intent(context, PlanDetailsActivity.class);
		intent.putExtra("plan", plan);
		return intent;
	}
}
