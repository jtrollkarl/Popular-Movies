package com.example.jay.udacitypopularmovies.activities;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.jay.udacitypopularmovies.MovieAdapter;
import com.example.jay.udacitypopularmovies.fragments.MovieFragment;
import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.RefreshMovies;


public class MainActivity extends AppCompatActivity {

    /**
     * Use a background thread (intent service) to fill the db. Then use a cursor loader
     * to load the ui based on the query given by the user (whether the list is sorted by
     * top rated or most popular).
     */

    private MovieAdapter adapter;
    private static final String TAG = MainActivity.class.getSimpleName();
    public static int SORT_METHOD;
    private boolean mTwoPane;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MovieFragment movieFragment = (MovieFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_movie);
        if(findViewById(R.id.details_container) != null){
            mTwoPane = true;
            if(savedInstanceState == null){
                //getSupportFragmentManager().beginTransaction().
            }
        }else{
            mTwoPane = false;
        }
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
}
