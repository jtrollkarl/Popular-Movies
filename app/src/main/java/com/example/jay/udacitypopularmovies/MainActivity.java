package com.example.jay.udacitypopularmovies;


import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{

    /**
     * Use a background thread (intent service) to fill the db. Then use a cursor loader
     * to load the ui based on the query given by the user (whether the list is sorted by
     * top rated or most popular).
     */

    private MovieAdapter adapter;

    @BindView(R.id.activity_main_recycler) RecyclerView recyclerView;

    private static final String TAG = MainActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        getSupportActionBar().hide();
        //layout manager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        //end layoutmanager

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(this, null);
        
        recyclerView.setAdapter(adapter);



        //FlowManager.getDatabase(PopularMoviesDatabase.class).getWritableDatabase();
        getSupportLoaderManager().initLoader(1, null, this);

    }




    //loader callbacks
    @Override
    public Loader onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "Creating loader");
        return new UILoader(getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader loader, Cursor cursor) {
        Log.d(TAG, "Load finished");
        //Log.d(TAG, cursor.getString(cursor.getColumnIndexOrThrow("title")));
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader loader) {
        Log.d(TAG, "Loader reset");
        adapter.swapCursor(null);
    }
}
