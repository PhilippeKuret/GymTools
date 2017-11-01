package com.example.philippe.gymtools.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.philippe.gymtools.R;
import com.example.philippe.gymtools.Tools.MathTools;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
{

    @BindView(R.id.weightInputConversion)
    EditText weightInput;

    @BindView(R.id.weightOutputConversion)
    TextView weightOutput;

    @BindView(R.id.weightTypeSwitch)
    Button weightTypeSwitch;

	@BindView(R.id.weightInputLabel)
	TextView weightInputLabel;

	@BindView(R.id.weightOutputLabel)
	TextView weightOutputLabel;

	@BindView(R.id.WorkoutTimerActivityNavigation)
	Button workoutTimerActivityNavigation;

    //1 = kilo, 2 = Lbs
    int weightType = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        weightTypeSwitch.setOnClickListener(new View.OnClickListener()
		{
            @Override
            public void onClick(View v)
			{
                if(weightType == 1)
                {
                    weightType = 2;
					weightInputLabel.setText("Lbs");
					weightOutputLabel.setText("Kg");

                }
                else
				{
					weightType = 1;
					weightInputLabel.setText("Kg");
					weightOutputLabel.setText("Lbs");
				}
            }
        });

        weightInput.setOnEditorActionListener(new TextView.OnEditorActionListener()
		{
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event)
			{
                boolean success = false;
                if(v.getText().length() != 0)
                {
                    double userInput = Double.parseDouble(v.getText().toString());
                    MathTools tools = new MathTools();
					if(weightType == 1)
					{
						weightOutput.setText(String.valueOf(tools.KiloToLbs(userInput)));
					}
					else
					{
						weightOutput.setText(String.valueOf(tools.LbsToKilo(userInput)));
					}
                    success = true;
                } else {
					weightOutput.setText("0.0");
				}

                return success;
            }
        });

		workoutTimerActivityNavigation.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View v)
			{
				Intent intent = new Intent(MainActivity.this, WorkoutTimerActivity.class);
				MainActivity.this.startActivity(intent);
			}
		});
    }
}