package com.example.jay.udacitypopularmovies;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.LocalBroadcastManager;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;


/**
 * Created by Jay on 2016-10-09.
 */

public class UILoader extends AsyncTaskLoader<Cursor> {

    public static final String ACTION_FORCE = UILoader.class.getSimpleName() + ":FORCE_LOAD";

    public UILoader(Context context) {
        super(context);
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
        FlowCursorList<Movie> list = SQLite.select()
                .from(Movie.class)
                .where()
                .orderBy(Movie_Table.popularity, false)
                .cursorList();

        //list.close();
        //loader handles the closing of the cursor?
        return list.cursor();
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
