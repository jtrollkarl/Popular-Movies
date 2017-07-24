package com.example.jay.udacitypopularmovies.application;

import android.app.Application;
import android.util.Log;

import com.example.jay.udacitypopularmovies.data.database.PopularMoviesDatabase;
import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by Jay on 2016-10-08.
 */

public class PopularMoviesApplication extends Application {
    private static final String TAG = PopularMoviesApplication.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this)
                .openDatabasesOnInit(true).build());
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "Terminating application, wiping database");
        FlowManager.getDatabase(PopularMoviesDatabase.class).destroy(this);
    }


}
