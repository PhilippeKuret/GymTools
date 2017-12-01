package com.example.philippe.gymtools.DI;

import com.example.philippe.gymtools.Activities.TrainingPlansActivity;
import com.example.philippe.gymtools.Activities.WorkoutPlanActivity;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})

public interface AppComponent {

	void inject(WorkoutPlanActivity target);

	void inject(TrainingPlansActivity target);
}
