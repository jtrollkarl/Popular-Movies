package com.example.jay.udacitypopularmovies;

import com.example.jay.udacitypopularmovies.apikey.MovieApiKey;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Jay on 2016-10-08.
 */

public interface PopularMoviesService {
    @GET("3/movie/{type}" + MovieApiKey.ApiKey)
    Call<Page> listMovies(@Path("type") String type);

}
