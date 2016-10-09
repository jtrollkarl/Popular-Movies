package com.example.jay.udacitypopularmovies;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Jay on 2016-10-08.
 */


@Database(name = PopularMoviesDatabase.NAME, version = PopularMoviesDatabase.VERSION)
public class PopularMoviesDatabase {

    public static final String NAME = "MoviesDatabase";
    public static final int VERSION = 1;


}
