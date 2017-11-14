package com.example.philippe.gymtools.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.example.philippe.gymtools.Activities.Adapters.WorkoutPlanAdapter;
import com.example.philippe.gymtools.Module.DatabaseService;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Presenter.WorkoutPlanPresenter;
import com.example.philippe.gymtools.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorkoutPlanActivity extends AppCompatActivity
{

	@BindView(R.id.list)
	RecyclerView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_workout_plan);
		ButterKnife.bind(this);


		DatabaseService db = new DatabaseService(this);

		listView.setLayoutManager(new LinearLayoutManager(this));

		WorkoutPlanPresenter planPresenter = new WorkoutPlanPresenter(this, db);

		planPresenter.getTrainingPlans();
	}

	public void setListView(List<TrainingPlan> trainingPlans)
	{
		listView.setAdapter(new WorkoutPlanAdapter(this, trainingPlans));
	}

	private TrainingPlan createPlan(int id, String name, boolean isDisplayed)
	{
		return new TrainingPlan(id, name, isDisplayed);
	}
}
