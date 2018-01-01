package com.example.philippe.gymtools.Module;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.philippe.gymtools.Module.Dao.ExerciseDao;
import com.example.philippe.gymtools.Module.Dao.TrainingPlanDao;
import com.example.philippe.gymtools.Module.Dao.WorkoutDao;
import com.example.philippe.gymtools.Objects.Exercise;
import com.example.philippe.gymtools.Objects.TrainingPlan;
import com.example.philippe.gymtools.Objects.Workout;

@Database(entities = {TrainingPlan.class, Exercise.class, Workout.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
	public abstract TrainingPlanDao trainingPlanDao();

	public abstract ExerciseDao exerciseDao();

	public abstract WorkoutDao workoutDao();

	private static AppDatabase INSTANCE;

	static AppDatabase getAppDatabase(Context context) {
		if (INSTANCE == null) {
			INSTANCE =
					Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "training-database")
							.build();
		}
		return INSTANCE;
	}

	public static void destroyInstance() {
		INSTANCE = null;
	}
}