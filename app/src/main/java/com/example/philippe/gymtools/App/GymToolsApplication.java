package com.example.philippe.gymtools.App;

import android.app.Application;

import com.example.philippe.gymtools.DI.AppComponent;
import com.example.philippe.gymtools.DI.AppModule;
import com.example.philippe.gymtools.DI.DaggerAppComponent;

public class GymToolsApplication extends Application
{
	private AppComponent appComponent;

	@Override
	public void onCreate()
	{
		super.onCreate();
		appComponent = initDagger(this);
	}

	public AppComponent getAppComponent() {
		return appComponent;
	}

	protected AppComponent initDagger(GymToolsApplication application) {
		return DaggerAppComponent.builder()
				.appModule(new AppModule(application))
				.build();
	}
}
