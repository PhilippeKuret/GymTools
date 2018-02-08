package com.example.philippe.gymtools.DI;

import com.example.philippe.gymtools.Activities.PlanDetailsActivity;
import com.example.philippe.gymtools.Activities.StartingActivity;
import com.example.philippe.gymtools.Activities.TrainingPlansActivity;
import com.example.philippe.gymtools.Activities.WorkoutPlanActivity;
import com.example.philippe.gymtools.Fragments.ExerciseDetailsDialogFragment;
import com.example.philippe.gymtools.Notification.TrainingPlanNotification;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})
public interface AppComponent {

	void inject(WorkoutPlanActivity target);

	void inject(TrainingPlansActivity target);

	void inject(PlanDetailsActivity target);

	void inject(ExerciseDetailsDialogFragment target);

	void inject(StartingActivity target);
}
