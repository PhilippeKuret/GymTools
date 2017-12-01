package com.example.philippe.gymtools.Module;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.philippe.gymtools.Module.Dao.TrainingPlanDao;
import com.example.philippe.gymtools.Objects.TrainingPlan;

@Database(entities = {TrainingPlan.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase
{
	public abstract TrainingPlanDao trainingPlanDao();

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