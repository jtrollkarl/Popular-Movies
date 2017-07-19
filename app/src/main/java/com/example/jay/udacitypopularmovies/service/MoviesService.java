package com.example.jay.udacitypopularmovies.service;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by Jay on 2017-07-16.
 */

public interface MoviesService {

    Single<List<Movie>> fetchMovies();

    Single<List<Movie>> fetchMoviesPage(int pageNumber);

}
