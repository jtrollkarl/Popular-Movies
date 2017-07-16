package com.example.jay.udacitypopularmovies.ui.fragment.moviesfragment;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.MoviesService;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by Jay on 2017-07-16.
 */

public class MoviesFragmentPresenter extends MvpBasePresenter<MovieFragmentContract.View> implements MovieFragmentContract.Actions {

    private final MoviesService moviesService;
    private final DatabaseService databaseService;
    private CompositeDisposable disposables;

    public MoviesFragmentPresenter(MoviesService moviesService, DatabaseService databaseService) {
        this.moviesService = moviesService;
        this.databaseService = databaseService;
    }

    @Override
    public void fetchMovies() {

    }

    @Override
    public void fetchMovies(int pageNumber) {

    }

    @Override
    public void onClickMovie(Movie movie) {

    }


}
