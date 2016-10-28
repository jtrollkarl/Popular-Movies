package com.example.jay.udacitypopularmovies.activities;


import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.jay.udacitypopularmovies.Movie;
import com.example.jay.udacitypopularmovies.adapters.MovieAdapter;
import com.example.jay.udacitypopularmovies.fragments.DetailFragment;
import com.example.jay.udacitypopularmovies.R;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieSelectedListener{

    /**
     * Use a background thread (intent service) to fill the db. Then use a cursor loader
     * to load the ui based on the query given by the user (whether the list is sorted by
     * top rated or most popular).
     */

    private static final String TAG = MainActivity.class.getSimpleName();
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
            detailFragment.update(movie);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.details_container, detailFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }
}
