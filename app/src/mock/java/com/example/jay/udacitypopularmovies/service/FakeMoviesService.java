package com.example.jay.udacitypopularmovies.service;

import com.example.jay.udacitypopularmovies.data.Movie;
import com.example.jay.udacitypopularmovies.data.ResultReviews;
import com.example.jay.udacitypopularmovies.data.ResultTrailer;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.Page;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultReviews;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultTrailer;
import com.example.jay.udacitypopularmovies.dbandmodels.Review;
import com.example.jay.udacitypopularmovies.dbandmodels.Trailer;
import com.example.jay.udacitypopularmovies.retrofitservice.RetrofitService;
>>>>>>> mvp_revise

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jay on 2017-07-16.
 */

public class FakeMoviesService implements RetrofitService {


    @Override
    public Single<Page> fetchMovies(@Path("type") String type, @Query("page") int page, @Query("api_key") String apiKey) {
        return new Single<Page>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super Page> observer) {

            }
        };
    }

    @Override
    public Single<Review> fetchReviews(@Path("id") String id, @Query("api_key") String apiKey) {
        return new Single<Review>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super Review> observer) {

            }
        };
    }

    @Override
    public Single<Trailer> fetchTrailers(@Path("id") String id, @Query("api_key") String apiKey) {
        return new Single<Trailer>() {
            @Override
            protected void subscribeActual(@NonNull SingleObserver<? super Trailer> observer) {

            }
        };
    }
}
