package com.example.jay.udacitypopularmovies.service;

import com.example.jay.udacitypopularmovies.apikey.MovieApiKey;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.Page;
import com.example.jay.udacitypopularmovies.misc.Urls;
import com.example.jay.udacitypopularmovies.retrofitservice.PopularMoviesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jay on 2017-07-16.
 */

public class MoviesServiceImpl implements MoviesService{

    // TODO: 2017-07-19 implement passing of different types
    private static final String TYPE = Page.POPULAR;

    @Override
    public Single<List<Movie>> fetchMovies() {
        return new Single<List<Movie>>() {
            @Override
            protected void subscribeActual(@NonNull final SingleObserver<? super List<Movie>> observer) {
                Call<Page> moviePage = buildPage(TYPE, 1);

                moviePage.enqueue(new Callback<Page>() {
                    @Override
                    public void onResponse(Call<Page> call, Response<Page> response) {
                        if (response.isSuccessful()) {
                            try {
                                observer.onSuccess(response.body().getResults());
                            } catch (NullPointerException e) {
                                observer.onError(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Page> call, Throwable t) {
                        observer.onError(t);
                    }
                });

            }
        };
    }

    @Override
    public Single<List<Movie>> fetchMoviesPage(final int pageNumber) {
        return new Single<List<Movie>>() {
            @Override
            protected void subscribeActual(@NonNull final SingleObserver<? super List<Movie>> observer) {
                Call<Page> moviePage = buildPage(TYPE, pageNumber);
                moviePage.enqueue(new Callback<Page>() {
                    @Override
                    public void onResponse(Call<Page> call, Response<Page> response) {
                        if (response.isSuccessful()) {
                            try {
                                observer.onSuccess(response.body().getResults());
                            } catch (NullPointerException e) {
                                observer.onError(e);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Page> call, Throwable t) {
                        observer.onError(t);
                    }
                });
            }
        };
    }

    private Call<Page> buildPage(String type, int page){
        PopularMoviesService popularMoviesService = buildRetrofit().create(PopularMoviesService.class);
        return  popularMoviesService.listMovies(type, page, MovieApiKey.ApiKey);
    }

    private Retrofit buildRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(Urls.TMDB_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }



}
