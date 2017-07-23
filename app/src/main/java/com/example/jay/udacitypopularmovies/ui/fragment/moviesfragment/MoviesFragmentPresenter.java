package com.example.jay.udacitypopularmovies.ui.fragment.moviesfragment;

import android.util.Log;

import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.loader.MovieLoader;
import com.example.jay.udacitypopularmovies.schedulers.BaseSchedulerProvider;
import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.MoviesService;
import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;

/**
 * Created by Jay on 2017-07-16.
 */

public class MoviesFragmentPresenter extends MvpBasePresenter<MovieFragmentContract.View> implements MovieFragmentContract.Actions {

    private static final String TAG = MoviesFragmentPresenter.class.getSimpleName();
    private final MoviesService moviesService;
    private final DatabaseService databaseService;
    private final BaseSchedulerProvider schedulerProvider;
    private final CompositeDisposable disposables;

    public MoviesFragmentPresenter( MoviesService moviesService, DatabaseService databaseService, BaseSchedulerProvider schedulerProvider) {
        this.moviesService = moviesService;
        this.databaseService = databaseService;
        this.schedulerProvider = schedulerProvider;
        this.disposables = new CompositeDisposable();
    }

    @Override
    public void fetchMovies(int id) {
        disposables.add(moviesService.fetchMovies(getType(id))
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<List<Movie>>() {
                    @Override
                    public void onSuccess(@NonNull List<Movie> movies) {
                        insertMovies(movies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (isViewAttached()) {
                            getView().showMessage(R.string.error_fetch_movies);
                            getView().showMovies();
                        }
                    }
                }));
    }

    @Override
    public void fetchMoviesPage(int id, int pageNumber) {
        moviesService.fetchMoviesPage(getType(id), pageNumber)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(new DisposableSingleObserver<List<Movie>>() {
                    @Override
                    public void onSuccess(@NonNull List<Movie> movies) {
                        insertMovies(movies);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (isViewAttached()){
                            getView().showMessage(R.string.error_fetch_movies);
                            getView().showMovies();
                        }
                    }
                });
    }

    @Override
    public void insertMovies(List<Movie> movies) {
        databaseService.insertMovies(movies)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        if (isViewAttached()) {
                            getView().showMovies();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        if (isViewAttached()) {
                            getView().showMessage(R.string.error_insert_movies);
                        }
                    }
                });
    }

    @Override
    public void onPause() {
        Log.d(TAG, "Clearing disposables");
        Log.d(TAG, "Size of disposables: " + disposables.size() + " " + disposables.toString());
        disposables.clear();
        Log.d(TAG, "After clear, size of disposables: " + disposables.size() + " " + disposables.toString());
    }

    @Override
    public void onClickMovie(Movie movie) {
        if (isViewAttached()) {
            getView().showMovieDetails(movie);
        }
    }

    @Override
    public void onClickSortPopular() {
        fetchMovies(MovieLoader.LOADER_ID_POPULAR);
    }

    @Override
    public void onClickSortTopRated() {
        fetchMovies(MovieLoader.LOADER_ID_TOP_RATED);
    }

    @Override
    public void onClickSortFavourites() {
        if(isViewAttached()){
            getView().showMovies();
        }
    }

    private String getType(int loaderId){
        switch (loaderId){
            case MovieLoader.LOADER_ID_POPULAR:
                return MoviesService.TYPE_POPULAR;
            case MovieLoader.LOADER_ID_TOP_RATED:
                return MoviesService.TYPE_TOP_RATED;
        }
        return MoviesService.TYPE_POPULAR;
    }
}
