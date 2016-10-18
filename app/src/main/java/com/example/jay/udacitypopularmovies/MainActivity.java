package com.example.jay.udacitypopularmovies;


import android.content.Intent;
import android.database.Cursor;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

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
    public static int SORT_METHOD;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(findViewById(R.id.details_container) != null){
            mTwoPane = true;


            if(savedInstanceState == null){
                //getSupportFragmentManager().beginTransaction().
            }
        }else{
            mTwoPane = false;
        }

        //getSupportActionBar().hide();
        //layout manager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        //end layoutmanager

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(this, null);
        recyclerView.addItemDecoration(new RecyclerViewItemDecorator(10));
        recyclerView.setAdapter(adapter);


        //FlowManager.getDatabase(PopularMoviesDatabase.class).getWritableDatabase();
        getSupportLoaderManager().initLoader(1, null, this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         if(item.getItemId() ==  R.id.menuSortPopularity && SORT_METHOD != R.id.menuSortPopularity) {
             SORT_METHOD = R.id.menuSortPopularity;
             Intent popularIntent = new Intent(this, RefreshMovies.class);
             popularIntent.setAction(RefreshMovies.ACTION_SWITCH_POPULAR);
             startService(popularIntent);
             return true;
         }
        else if(item.getItemId() == R.id.menuSortRating && SORT_METHOD != R.id.menuSortRating){
             SORT_METHOD = R.id.menuSortRating;
             Intent topRatedIntent = new Intent(this, RefreshMovies.class);
             topRatedIntent.setAction(RefreshMovies.ACTION_SWTICH_TOP_RATED);
             startService(topRatedIntent);
             return true;
         }
        return true;
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
