package com.example.jay.udacitypopularmovies;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.content.AsyncTaskLoader;

import com.raizlabs.android.dbflow.list.FlowCursorList;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import java.util.List;

/**
 * Created by Jay on 2016-10-09.
 */

public class UILoader extends AsyncTaskLoader<Cursor> {

    public UILoader(Context context) {
        super(context);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();

    }

    @Override
    public Cursor loadInBackground() {
        FlowCursorList<Movie> list = SQLite.select()
                .from(Movie.class)
                .where()
                .orderBy(Movie_Table.popularity, true)
                .cursorList();

        list.close();
        return list.cursor();
    }

    @Override
    public void deliverResult(Cursor data) {
        super.deliverResult(data);
    }
}
