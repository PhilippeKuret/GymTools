package com.example.philippe.gymtools.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.philippe.gymtools.Activities.ViewInterface.PlanDetailsView;
import com.example.philippe.gymtools.R;

public class PlanDetails extends AppCompatActivity implements PlanDetailsView
{

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_plan_details);
	}
}
