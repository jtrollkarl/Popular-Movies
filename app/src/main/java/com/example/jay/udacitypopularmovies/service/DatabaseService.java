package com.example.jay.udacitypopularmovies.service;

import com.example.jay.udacitypopularmovies.data.model.Movie;

import java.util.List;

import io.reactivex.Completable;

/**
 * Created by Jay on 2017-07-16.
 */

public interface DatabaseService {

    Completable insertMovie(Movie movie);

    Completable insertMovies(List<Movie> movies);

    Completable deleteMovie(Movie movie);

    Completable saveFavourite(Movie movie);

}
