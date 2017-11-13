package com.example.philippe.gymtools.Tools;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.example.philippe.gymtools.Module.AppDatabase;

public class DatabaseInitializer
{
	public static void populateAsync(@NonNull final AppDatabase db) {
		PopulateDbAsync task = new PopulateDbAsync(db);
		task.execute();
	}

	private static class PopulateDbAsync extends AsyncTask<Void, Void, Void>
	{

		private final AppDatabase mDb;

		PopulateDbAsync(AppDatabase db) {
			mDb = db;
		}

		@Override
		protected Void doInBackground(Void... voids)
		{
			return null;
		}
	}
}
