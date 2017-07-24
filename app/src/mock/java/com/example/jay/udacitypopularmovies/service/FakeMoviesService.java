package com.example.jay.udacitypopularmovies.service;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultReviews;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultTrailer;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;

/**
 * Created by Jay on 2017-07-16.
 */

public class FakeMoviesService implements MoviesService {

    @Override
    public Single<List<Movie>> fetchMovies(String type) {
        return new Single<List<Movie>>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super List<Movie>> observer) {
                observer.onSuccess(Movie.getFakes(20));
            }
        };
    }

    @Override
    public Single<List<Movie>> fetchMoviesPage(String type, int pageNumber) {
        return new Single<List<Movie>>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super List<Movie>> observer) {
                observer.onSuccess(Movie.getFakes(20));
            }
        };
    }

    @Override
    public Single<List<ResultTrailer>> fetchTrailers(String movieId) {
        return new Single<List<ResultTrailer>>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super List<ResultTrailer>> observer) {
                observer.onSuccess(ResultTrailer.getFakes(1));
            }
        };
    }

    @Override
    public Single<List<ResultReviews>> fetchReviews(String movieId) {
        return new Single<List<ResultReviews>>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super List<ResultReviews>> observer) {
                observer.onSuccess(ResultReviews.getFakes(2));
            }
        };
    }
}
