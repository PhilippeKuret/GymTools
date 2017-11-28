package com.example.philippe.gymtools.DI;

import com.example.philippe.gymtools.Activities.WorkoutPlanActivity;
import com.example.philippe.gymtools.Module.DatabaseService;
import com.example.philippe.gymtools.Presenter.WorkoutPlanPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, PresenterModule.class})

public interface AppComponent {

	void inject(WorkoutPlanActivity target);
}
