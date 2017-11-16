package com.example.philippe.gymtools.DI;

import com.example.philippe.gymtools.Presenter.PresenterInterface.WorkoutPlanInterface;
import com.example.philippe.gymtools.Presenter.WorkoutPlanPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

	@Provides
	@Singleton
	WorkoutPlanInterface provideWorkoutPlanPresenter() {
		return new WorkoutPlanPresenter();
	}
}