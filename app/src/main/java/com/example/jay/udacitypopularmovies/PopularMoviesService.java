package com.example.jay.udacitypopularmovies;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Jay on 2016-10-08.
 */

public interface PopularMoviesService {
    @GET("/3/movie/{type}")
    Call<Page> listMovies(@Path("type") String type, @Query("api_key") String apiKey);

}
