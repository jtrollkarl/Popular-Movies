package com.example.jay.udacitypopularmovies.ui.activity.mainactivity;


import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.adapters.MovieAdapter;
import com.example.jay.udacitypopularmovies.ui.fragment.detailsfragment.DetailFragment;
import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.ui.fragment.moviesfragment.MovieFragment;
import com.example.jay.udacitypopularmovies.ui.activity.detail.DetailActivity;


public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieSelectedListener{

    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadMoviesFragment();

        mTwoPane = findViewById(R.id.details_container) != null;
    }

    private void loadMoviesFragment(){
        Fragment moviesFragment = new MovieFragment();
        FragmentTransaction moviesTransaction = getSupportFragmentManager().beginTransaction();
        moviesTransaction.replace(R.id.container_movies, moviesFragment);
        moviesTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        moviesTransaction.commit();
    }

    @Override
    public void onMovieSelected(Movie movie) {

        if(!mTwoPane){
            Log.d(TAG, "detail_container not found");
            Intent detailIntent = new Intent(MainActivity.this, DetailActivity.class);
            detailIntent.putExtra("movie", movie);
            startActivity(detailIntent);
        }else{
            Log.d(TAG, "detail_container found");
            DetailFragment detailFragment = new DetailFragment();
            Bundle movieBundle = new Bundle();
            movieBundle.putParcelable(DetailFragment.MOVIE_KEY, movie);
            detailFragment.setArguments(movieBundle);

            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.details_container, detailFragment);
            transaction.addToBackStack(null);
            transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            transaction.commit();
        }
    }
}
