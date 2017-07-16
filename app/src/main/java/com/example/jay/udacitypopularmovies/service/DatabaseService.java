package com.example.jay.udacitypopularmovies.service;

import android.database.Cursor;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;

import java.util.List;

/**
 * Created by Jay on 2017-07-16.
 */

public interface DatabaseService {

    void insertMovie(Movie movie);

    void insertMovies(List<Movie> movies);

    void deleteMovie(Movie movie);

    void saveFavourite(Movie movie);

    Cursor fetchMovies();
}
