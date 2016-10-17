package com.example.jay.udacitypopularmovies;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;



public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.poster) ImageView poster;
    @BindView(R.id.detailMovie) ImageView detailMovie;
    @BindView(R.id.movieSynopsis) TextView movieSynopsis;
    @BindView(R.id.movieRating) TextView movieRating;
    @BindView(R.id.movieRelease) TextView movieRelease;
    @BindView(R.id.movieTitle) TextView movieTitle;

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    public static final String TAG = DetailActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.inflateMenu(R.menu.menu_detail);
        setSupportActionBar(toolbar);
        //getSupportActionBar().hide();

        Movie movie = getIntent().getParcelableExtra("movie");
        loadMovie(movie);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.menu_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void loadMovie(Movie movie){
        toolbar.setTitle(movie.getTitle());
        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w500//" + movie.getBackdropPath())
                .into(poster);

        Picasso.with(this)
                .load("http://image.tmdb.org/t/p/w185//"+ movie.getPosterPath())
                .into(detailMovie);

        movieSynopsis.setText(movie.getOverview());
        movieRating.setText(String.valueOf(movie.getVoteAverage()));
        movieTitle.setText(movie.getTitle());
        movieRelease.setText(movie.getReleaseDate());
        this.setTitle(movie.getTitle());


    }




}
