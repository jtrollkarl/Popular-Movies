package com.example.jay.udacitypopularmovies.ui.fragment.detailsfragment;

import android.util.Log;

import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.apikey.MovieApiKey;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultReviews;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultTrailer;
import com.example.jay.udacitypopularmovies.dbandmodels.Review;
import com.example.jay.udacitypopularmovies.dbandmodels.Trailer;
import com.example.jay.udacitypopularmovies.retrofitservice.RetrofitService;
import com.example.jay.udacitypopularmovies.schedulers.BaseSchedulerProvider;
import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.MoviesService;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Jay on 2017-07-16.
 */

public class DetailsFragmentPresenter extends MvpBasePresenter<DetailsFragmentContract.View> implements DetailsFragmentContract.Actions {

    private static final String TAG = DetailsFragmentPresenter.class.getSimpleName();
    private final RetrofitService moviesService;
    private final DatabaseService databaseService;
    private final BaseSchedulerProvider schedulerProvider;
    private final CompositeDisposable disposable;

    public DetailsFragmentPresenter(RetrofitService moviesService, DatabaseService databaseService, BaseSchedulerProvider schedulerProvider) {
        this.moviesService = moviesService;
        this.databaseService = databaseService;
        this.schedulerProvider = schedulerProvider;
        disposable = new CompositeDisposable();
    }

    @Override
    public void onClickFavourite(Movie favouriteMovie) {
        disposable.add(databaseService.saveFavourite(favouriteMovie)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableCompletableObserver() {
                    @Override
                    public void onComplete() {
                        if(isViewAttached()){
                            getView().showMessage(R.string.success_save_movie);
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if(isViewAttached()){
                            getView().showMessage(R.string.error_save_favourite);
                        }
                    }
                }));
    }

    @Override
    public void loadMovie(Movie movie) {
        if(isViewAttached()){
            getView().showMovie(movie);
        }
    }

    @Override
    public void fetchReviews(String movieId) {
        disposable.add(moviesService.fetchReviews(movieId, MovieApiKey.ApiKey)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<Review>() {
                    @Override
                    public void onSuccess(@NonNull Review reviewResult) {
                        if (isViewAttached()) {
                            getView().showReviews(reviewResult.getResults());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (isViewAttached()) {
                            getView().showMessage(R.string.error_fetch_reviews);
                        }
                    }
                }));
    }

    @Override
    public void fetchTrailers(String movieId) {
        disposable.add(moviesService.fetchTrailers(movieId, MovieApiKey.ApiKey)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<Trailer>() {
                    @Override
                    public void onSuccess(@NonNull Trailer trailerResult) {
                        if (isViewAttached()) {
                            getView().showTrailers(trailerResult.getResults());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (isViewAttached()) {
                            getView().showMessage(R.string.error_fetch_trailers);
                        }
                    }
                }));
    }

    @Override
    public void onPause() {
        disposable.clear();
    }
}
