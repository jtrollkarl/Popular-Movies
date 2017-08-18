package com.example.jay.udacitypopularmovies.loader;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import com.example.jay.udacitypopularmovies.data.model.Favourite;

import com.example.jay.udacitypopularmovies.data.model.Favourite_Table;
import com.example.jay.udacitypopularmovies.data.model.Movie;


import com.example.jay.udacitypopularmovies.data.model.Movie_Table;
import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;


/**
 * Created by Jay on 2016-10-09.
 */

public class MovieLoader extends AsyncTaskLoader<Cursor> {

    public static final String ACTION_FORCE = MovieLoader.class.getSimpleName() + ":FORCE_LOAD";
    public static final int LOADER_ID_POPULAR = 1;
    public static final int LOADER_ID_TOP_RATED = 2;
    public static final int LOADER_ID_FAVOURITES = 3;
    private static final String TAG = MovieLoader.class.getSimpleName();

    public MovieLoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public Cursor loadInBackground() {
        Log.d(TAG, String.valueOf(getId()));

        switch (getId()){
            case LOADER_ID_POPULAR:
                FlowCursorList<Movie> listPopular = SQLite.select()
                        .from(Movie.class)
                        .where()
                        .orderBy(Movie_Table.popularity, false)
                        .cursorList();
                return listPopular.cursor();
            case LOADER_ID_TOP_RATED:
                FlowCursorList<Movie> listTopRated = SQLite.select()
                        .from(Movie.class)
                        .where()
                        .orderBy(Movie_Table.voteAverage, false)
                        .cursorList();
                return listTopRated.cursor();
            case LOADER_ID_FAVOURITES:
                FlowCursorList<Favourite> listFav = SQLite.select()
                        .from(Favourite.class)
                        .orderBy(Favourite_Table.popularity, false)
                        .cursorList();
                return listFav.cursor();
        }
        return null;
    }

    @Override
    public void deliverResult(Cursor data) {
        super.deliverResult(data);
    }

}
