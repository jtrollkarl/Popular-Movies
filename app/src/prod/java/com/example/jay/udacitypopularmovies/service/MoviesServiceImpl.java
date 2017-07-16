package com.example.jay.udacitypopularmovies.service;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Jay on 2017-07-16.
 */

public class MoviesServiceImpl implements MoviesService {
    @Override
    public Observable<List<Movie>> fetchMovies(int pageNumber) {
        return null;
    }
}
