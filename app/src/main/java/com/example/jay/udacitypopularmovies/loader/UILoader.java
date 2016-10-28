package com.example.jay.udacitypopularmovies.loader;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jay.udacitypopularmovies.dbandmodels.Favourite;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie_Table;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;


/**
 * Created by Jay on 2016-10-09.
 */

public class UILoader extends AsyncTaskLoader<Cursor> {

    public static final String ACTION_FORCE = UILoader.class.getSimpleName() + ":FORCE_LOAD";
    private static final String TAG = UILoader.class.getSimpleName();
    private int LOADER_ID;

    public UILoader(int LOADER_ID, Context context) {
        super(context);
        this.LOADER_ID = LOADER_ID;
    }

    @Override
    protected void onStartLoading() {
        LocalBroadcastManager localBroadcastManager = LocalBroadcastManager.getInstance(getContext());
        IntentFilter filter = new IntentFilter(ACTION_FORCE);
        localBroadcastManager.registerReceiver(broadcastReceiver, filter);
        forceLoad();

    }

    @Override
    public Cursor loadInBackground() {
        Log.d(TAG, String.valueOf(LOADER_ID));

        switch (LOADER_ID){
            case 1:
                FlowCursorList<Movie> listPopular = SQLite.select()
                        .from(Movie.class)
                        .where()
                        .orderBy(Movie_Table.popularity, false)
                        .cursorList();
                return listPopular.cursor();
            case 2:
                FlowCursorList<Movie> listTop_Rated = SQLite.select()
                        .from(Movie.class)
                        .where()
                        .orderBy(Movie_Table.voteAverage, false)
                        .cursorList();
                return listTop_Rated.cursor();
            case 3:
                FlowCursorList<Favourite> listFav = SQLite.select()
                        .from(Favourite.class)
                        .where()
                        .cursorList();
                return listFav.cursor();

        }

/*        if(PROPERTY == null){
            PROPERTY = Movie_Table.popularity;
        }

        FlowCursorList<Movie> list = SQLite.select()
                .from(Movie.class)
                .where()
                .orderBy(PROPERTY, false)
                .cursorList();

        //list.close();
        //loader handles the closing of the cursor?
        return list.cursor();*/
        return null;
    }



    @Override
    public void deliverResult(Cursor data) {
        super.deliverResult(data);
    }


    @Override
    protected void onReset() {
        super.onReset();
        LocalBroadcastManager.getInstance(getContext()).unregisterReceiver(broadcastReceiver);
    }

    private BroadcastReceiver broadcastReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ACTION_FORCE)){
                forceLoad();
            }
        }
    };
}
