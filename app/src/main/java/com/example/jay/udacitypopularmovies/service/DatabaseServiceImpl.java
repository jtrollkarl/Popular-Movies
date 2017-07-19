package com.example.jay.udacitypopularmovies.service;

import android.support.annotation.NonNull;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.PopularMoviesDatabase;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;

/**
 * Created by Jay on 2017-07-16.
 */

public class DatabaseServiceImpl implements DatabaseService {

    @Override
    public Completable insertMovie(final Movie movie) {
        return new Completable() {
            @Override
            protected void subscribeActual(final CompletableObserver s) {
                FlowManager.getDatabase(PopularMoviesDatabase.class)
                        .beginTransactionAsync(new ITransaction() {
                            @Override
                            public void execute(DatabaseWrapper databaseWrapper) {
                                movie.update();
                                movie.save();
                            }
                        }).success(new Transaction.Success() {
                    @Override
                    public void onSuccess(@NonNull Transaction transaction) {
                        s.onComplete();
                    }
                }).error(new Transaction.Error() {
                    @Override
                    public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                        s.onError(error);
                    }
                }).build();
            }
        };
    }

    @Override
    public Completable insertMovies(final List<Movie> movies) {
        return new Completable() {
            @Override
            protected void subscribeActual(final CompletableObserver s) {
                FlowManager.getDatabase(PopularMoviesDatabase.class)
                        .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                                new ProcessModelTransaction.ProcessModel<Movie>() {
                                    @Override
                                    public void processModel(Movie movie, DatabaseWrapper wrapper) {
                                        movie.update();
                                        movie.save();
                                    }

                                }).addAll(movies).build())
                        .error(new Transaction.Error() {
                            @Override
                            public void onError(Transaction transaction, Throwable error) {
                                s.onError(error);
                            }
                        })
                        .success(new Transaction.Success() {
                            @Override
                            public void onSuccess(Transaction transaction) {
                                s.onComplete();
                            }
                        }).build().execute();
            }
        };
    }

    @Override
    public Completable deleteMovie(final Movie movie) {
        return new Completable() {
            @Override
            protected void subscribeActual(final CompletableObserver s) {
                FlowManager.getDatabase(PopularMoviesDatabase.class)
                        .beginTransactionAsync(new ITransaction() {
                            @Override
                            public void execute(DatabaseWrapper databaseWrapper) {
                                movie.delete();
                                movie.save();
                            }
                        })
                        .success(new Transaction.Success() {
                            @Override
                            public void onSuccess(@NonNull Transaction transaction) {
                                s.onComplete();
                            }
                        })
                        .error(new Transaction.Error() {
                            @Override
                            public void onError(@NonNull Transaction transaction, @NonNull Throwable error) {
                                s.onError(error);
                            }
                        })
                .build();
            }
        };
    }

    @Override
    public Completable saveFavourite(Movie movie) {
        return null;
    }

}
