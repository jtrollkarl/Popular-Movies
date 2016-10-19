package com.example.jay.udacitypopularmovies.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.udacitypopularmovies.Movie;
import com.example.jay.udacitypopularmovies.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    @BindView(R.id.poster) ImageView poster;
    @BindView(R.id.detailMovie) ImageView detailMovie;
    @BindView(R.id.movieSynopsis) TextView movieSynopsis;
    @BindView(R.id.movieRating) TextView movieRating;
    @BindView(R.id.movieRelease) TextView movieRelease;
    @BindView(R.id.movieTitle) TextView movieTitle;

    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

    }


    public void loadMovie(Movie movie){
        //toolbar.setTitle(movie.getTitle());
        Picasso.with(getActivity())
                .load("http://image.tmdb.org/t/p/w500//" + movie.getBackdropPath())
                .into(poster);

        Picasso.with(getActivity())
                .load("http://image.tmdb.org/t/p/w185//"+ movie.getPosterPath())
                .into(detailMovie);

        movieSynopsis.setText(movie.getOverview());
        movieRating.setText(String.valueOf(movie.getVoteAverage()));
        movieTitle.setText(movie.getTitle());
        movieRelease.setText(movie.getReleaseDate());
        //toolbar.setTitle(movie.getTitle());


    }


}
