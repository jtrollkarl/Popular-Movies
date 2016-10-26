package com.example.jay.udacitypopularmovies.activities;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jay.udacitypopularmovies.Movie;
import com.example.jay.udacitypopularmovies.MovieAdapter;
import com.example.jay.udacitypopularmovies.fragments.DetailFragment;
import com.example.jay.udacitypopularmovies.fragments.MovieFragment;
import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.RefreshMovies;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieSelectedListener{

    /**
     * Use a background thread (intent service) to fill the db. Then use a cursor loader
     * to load the ui based on the query given by the user (whether the list is sorted by
     * top rated or most popular).
     */

    private static final String TAG = MainActivity.class.getSimpleName();
    public static int SORT_METHOD;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(findViewById(R.id.details_container) != null){
            mTwoPane = true;
        }else{
            mTwoPane = false;
        }
    }

    @Override
    public void onMovieSelected(Movie movie) {

        if(!mTwoPane){
            Log.d(TAG, "detail_container id not found");
            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            //add extra data to intent
            detailIntent.putExtra("movie", movie);
            //start intent
            startActivity(detailIntent);
        }else{
            Log.d(TAG, "detail_container id found");
            DetailFragment detailFragment = new DetailFragment();
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            detailFragment.loadMovieTablet(movie);
            transaction.replace(R.id.details_container, detailFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }

    }


/*    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

         if(item.getItemId() ==  R.id.menuSortPopularity) {
             SORT_METHOD = R.id.menuSortPopularity;
             Intent popularIntent = new Intent(this, RefreshMovies.class);
             popularIntent.setAction(RefreshMovies.ACTION_SWITCH_POPULAR);
             startService(popularIntent);
             return true;
         }
        else if(item.getItemId() == R.id.menuSortRating){
             SORT_METHOD = R.id.menuSortRating;
             Intent topRatedIntent = new Intent(this, RefreshMovies.class);
             topRatedIntent.setAction(RefreshMovies.ACTION_SWTICH_TOP_RATED);
             startService(topRatedIntent);
             return true;
         }
        else if(item.getItemId() == R.id.menuSortFavourites){
             SORT_METHOD = R.id.menuSortFavourites;
             return true;

         }
        return true;
    }*/


}
