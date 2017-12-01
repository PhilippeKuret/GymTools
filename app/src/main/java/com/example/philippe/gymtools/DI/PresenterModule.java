package com.example.philippe.gymtools.DI;

import com.example.philippe.gymtools.Presenter.PresenterInterface.TrainingPlansInterface;
import com.example.philippe.gymtools.Presenter.PresenterInterface.WorkoutPlanInterface;
import com.example.philippe.gymtools.Presenter.TrainingPlansPresenter;
import com.example.philippe.gymtools.Presenter.WorkoutPlanPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class PresenterModule {

	@Provides
	@Singleton
	WorkoutPlanInterface provideWorkoutPlanPresenter() {
		return new WorkoutPlanPresenter();
	}

	@Provides
	@Singleton
	TrainingPlansInterface provideTrainingPlansInterface() { return new TrainingPlansPresenter(); }
}