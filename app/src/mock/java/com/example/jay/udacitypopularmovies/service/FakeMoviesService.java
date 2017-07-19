package com.example.jay.udacitypopularmovies.service;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;

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
    public Single<List<Movie>> fetchMovies() {
        return new Single<List<Movie>>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super List<Movie>> observer) {
                // TODO: 2017-07-18 write some fake data here
                observer.onSuccess(new ArrayList<Movie>() {
                });

            }
        };
    }

    @Override
    public Single<List<Movie>> fetchMoviesPage(int pageNumber) {
        return null;
    }
}
