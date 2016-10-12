package com.example.jay.udacitypopularmovies;

import android.util.Log;

import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.config.DatabaseDefinition;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;

/**
 * Created by Jay on 2016-10-09.
 */

public class DatabaseStorageRetrieval {

    private static final String TAG = DatabaseStorageRetrieval.class.getSimpleName();
    private int count;

    public void insert(final ArrayList<Movie> movies){

        Log.w(TAG, "db class started");

        FlowManager.getDatabase(PopularMoviesDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<Movie>() {
                            @Override
                            public void processModel(Movie movie) {
                                // do work here -- i.e. user.delete() or user.update()
                                Log.d(TAG, String.valueOf(movie.getId()));
                                movie.insert();
                                movie.save();
                            }
                        }).addAll(movies).build())  // add elements (can also handle multiple)
                .error(new Transaction.Error() {
                    @Override
                    public void onError(Transaction transaction, Throwable error) {
                        Log.e(TAG, "ERROR");
                        error.printStackTrace();
                    }
                })
                .success(new Transaction.Success() {
                    @Override
                    public void onSuccess(Transaction transaction) {
                        Log.d(TAG, "Success");
                    }
                }).build().execute();


    }//end insert




}
