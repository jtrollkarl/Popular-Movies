package com.example.jay.udacitypopularmovies.application;

import android.app.Application;
import android.content.Intent;

import com.example.jay.udacitypopularmovies.service.RefreshMovies;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Jay on 2016-10-08.
 */

public class PopularMoviesApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build());

        Intent startIntent = new Intent(this, RefreshMovies.class);
        startIntent.setAction(RefreshMovies.ACTION_APP_START);
        startService(startIntent);
    }
}
