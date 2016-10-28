package com.example.jay.udacitypopularmovies.dbandmodels;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.example.jay.udacitypopularmovies.loader.UILoader;
import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;

/**
 * Created by Jay on 2016-10-09.
 */

public class DatabaseStorageRetrieval {

    private static final String TAG = DatabaseStorageRetrieval.class.getSimpleName();



    public static void insert(final Context context, final ArrayList<Movie> movies){

        FlowManager.getDatabase(PopularMoviesDatabase.class)
                .beginTransactionAsync(new ProcessModelTransaction.Builder<>(
                        new ProcessModelTransaction.ProcessModel<Movie>() {
                            @Override
                            public void processModel(Movie movie) {
                                // do work here -- i.e. user.delete() or user.update()
                                //Log.d(TAG, String.valueOf(movie.getId()));
                                //movie.insert();

                                movie.update();
                                movie.save();
                                //Log.d(TAG, "Inserting movie");

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
                        Intent intent = new Intent(UILoader.ACTION_FORCE);
                        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                    }
                }).build().execute();


    }//end insert




}
