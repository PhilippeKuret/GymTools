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

import com.example.philippe.gymtools.Activities.Adapters.PlanDetailsAdapter;
import com.example.philippe.gymtools.Activities.ViewInterface.PlanDetailsView;
import com.example.philippe.gymtools.App.GymToolsApplication;
import com.example.philippe.gymtools.Fragments.CreateExerciseDialogFragment;
import com.example.philippe.gymtools.Fragments.ExerciseDetailsDialogFragment;
import com.example.philippe.gymtools.Module.AppDatabase;
import com.example.philippe.gymtools.Objects.Exercise;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.PresenterInterface.PlanDetailsInterface;
import com.example.philippe.gymtools.R;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PlanDetailsActivity extends AppCompatActivity implements
		PlanDetailsView,
		CreateExerciseDialogFragment.OnExerciseCreatedListener,
		PlanDetailsAdapter.onButtonClickFunction,
		PlanDetailsAdapter.OnItemClickListener
{
	@BindView(R.id.ExerciseList)
	RecyclerView exerciseRecyclerView;

	@BindView(R.id.planName)
	TextView planName;

	@BindView(R.id.newExerciseButton)
	Button newExerciseButton;

	@Inject
	PlanDetailsInterface planDetailsPresenter;

	private PlanDetailsAdapter adapter;

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

		exerciseRecyclerView.setLayoutManager(new LinearLayoutManager(this));

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
		planDetailsPresenter.createExercise(name, selectedTrainingPlan.getId());
		exerciseRecyclerView.setAdapter(null);
		planDetailsPresenter.getExercises(selectedTrainingPlan.getId());
	}


	@Override
	public void onListItemDeleteButtonClick(Exercise exercise)
	{
		planDetailsPresenter.deleteExercise(exercise);
		exerciseRecyclerView.setAdapter(null);
		planDetailsPresenter.getExercises(selectedTrainingPlan.getId());
	}

	@Override
	public void onListItemClick(Exercise exercise)
	{
		FragmentManager fm = getSupportFragmentManager();
		ExerciseDetailsDialogFragment exerciseDetailsDialogFragment = new ExerciseDetailsDialogFragment();
		exerciseDetailsDialogFragment.createBundle(exercise);
		exerciseDetailsDialogFragment.show(fm, "exercise_details");
	}

	public void setExercisesInView(List<Exercise> exercises)
	{
		adapter = new PlanDetailsAdapter(PlanDetailsActivity.this, exercises);
		exerciseRecyclerView.setAdapter(adapter);
	}

	public static Intent createIntent(Context context, TrainingPlan plan)
	{
		Intent intent = new Intent(context, PlanDetailsActivity.class);
		intent.putExtra("plan", plan);
		return intent;
	}
}
