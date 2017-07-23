package com.example.jay.udacitypopularmovies.service;

import android.util.Log;

import com.example.jay.udacitypopularmovies.apikey.MovieApiKey;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.Page;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultReviews;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultTrailer;
import com.example.jay.udacitypopularmovies.dbandmodels.Review;
import com.example.jay.udacitypopularmovies.dbandmodels.Trailer;
import com.example.jay.udacitypopularmovies.misc.Urls;
import com.example.jay.udacitypopularmovies.retrofitservice.PopularMoviesService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Jay on 2017-07-16.
 */

public class MoviesServiceImpl implements MoviesService {

    private static final String TAG = MoviesServiceImpl.class.getSimpleName();

    @Override
    public Single<List<Movie>> fetchMovies(final String type) {
        return new Single<List<Movie>>() {
            @Override
            protected void subscribeActual(@NonNull final SingleObserver<? super List<Movie>> observer) {
                Call<Page> moviePage = buildPageCall(type, 1);

                moviePage.enqueue(new Callback<Page>() {
                    @Override
                    public void onResponse(Call<Page> call, Response<Page> response) {
                        if (response.isSuccessful()) {
                            try {
                                observer.onSuccess(response.body().getResults());
                                Log.d(TAG, String.valueOf(response.body().getResults().size()));
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
    public Single<List<Movie>> fetchMoviesPage(final String type, final int pageNumber) {
        Log.d(TAG, "fetch movies page called " + pageNumber );
        return new Single<List<Movie>>() {
            @Override
            protected void subscribeActual(@NonNull final SingleObserver<? super List<Movie>> observer) {
                Call<Page> moviePage = buildPageCall(type, pageNumber);
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
    public Single<List<ResultTrailer>> fetchTrailers(final String movieId) {
        return new Single<List<ResultTrailer>>() {
            @Override
            protected void subscribeActual(@NonNull final SingleObserver<? super List<ResultTrailer>> observer) {
                Call<Trailer> trailerCall = buildTrailerCall(movieId);
                trailerCall.enqueue(new Callback<Trailer>() {
                    @Override
                    public void onResponse(Call<Trailer> call, Response<Trailer> response) {
                        try {
                            observer.onSuccess(response.body().getResults());
                        } catch (NullPointerException e) {
                            observer.onError(e);
                        }
                    }

                    @Override
                    public void onFailure(Call<Trailer> call, Throwable t) {
                        observer.onError(t);
                    }
                });
            }
        };
    }

    @Override
    public Single<List<ResultReviews>> fetchReviews(final String movieId) {
        return new Single<List<ResultReviews>>() {
            @Override
            protected void subscribeActual(@NonNull final SingleObserver<? super List<ResultReviews>> observer) {
                Call<Review> reviewCall = buildReviewCall(movieId);
                reviewCall.enqueue(new Callback<Review>() {
                    @Override
                    public void onResponse(Call<Review> call, Response<Review> response) {
                        try {
                            observer.onSuccess(response.body().getResults());
                        } catch (NullPointerException e) {
                            observer.onError(e);
                        }
                    }

                    @Override
                    public void onFailure(Call<Review> call, Throwable t) {
                        observer.onError(t);
                    }
                });
            }
        };
    }

    private Call<Trailer> buildTrailerCall(String id) {
        PopularMoviesService popularMoviesService = buildRetrofit().create(PopularMoviesService.class);
        return popularMoviesService.listTrailers(id, MovieApiKey.ApiKey);
    }

    private Call<Review> buildReviewCall(String id) {
        PopularMoviesService popularMoviesService = buildRetrofit().create(PopularMoviesService.class);
        return popularMoviesService.listReviews(id, MovieApiKey.ApiKey);
    }

    private Call<Page> buildPageCall(String type, int page) {
        PopularMoviesService popularMoviesService = buildRetrofit().create(PopularMoviesService.class);
        return popularMoviesService.listMovies(type, page, MovieApiKey.ApiKey);
    }

    private Retrofit buildRetrofit() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(Urls.TMDB_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


}
