package com.example.jay.udacitypopularmovies.retrofitservice;


import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.Page;
import com.example.jay.udacitypopularmovies.dbandmodels.Review;
import com.example.jay.udacitypopularmovies.dbandmodels.Trailer;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jay on 2016-10-08.
 */

public interface RetrofitService {

    @GET("/3/movie/{type}")
    Single<Page> fetchMovies(@Path("type") String type, @Query("page") int page, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}/reviews")
    Single<Review> fetchReviews(@Path("id") String id, @Query("api_key") String apiKey);

    @GET("/3/movie/{id}/videos")
    Single<Trailer> fetchTrailers(@Path("id") String id, @Query("api_key") String apiKey);

}
