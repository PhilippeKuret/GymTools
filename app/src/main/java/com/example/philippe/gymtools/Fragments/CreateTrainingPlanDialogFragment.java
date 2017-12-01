package com.example.philippe.gymtools.Fragments;

import android.support.v4.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.philippe.gymtools.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateTrainingPlanDialogFragment extends DialogFragment
{
	@BindView(R.id.newPlanName)
	EditText newPlanName;

	@BindView(R.id.isPlanShownRadioButton)
	RadioButton isPlanShown;

	@BindView(R.id.acceptNewPlanButton)
	Button acceptNewPlanButton;

	public interface OnPlanCreatedListener
	{
		void OnPlanCreated(String name, Boolean isShown);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.fragment_create_training_plan, container);
	}

	@Override
	public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
	{
		super.onViewCreated(view, savedInstanceState);
		ButterKnife.bind(this, view);

		acceptNewPlanButton.setOnClickListener(v ->
		{
			OnPlanCreatedListener listener = (OnPlanCreatedListener) getActivity();
			listener.OnPlanCreated(newPlanName.getText().toString(), isPlanShown.isChecked());
			dismiss();
		});
	}
}
