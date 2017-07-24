package com.example.jay.udacitypopularmovies;

import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.DatabaseServiceImpl;
import com.example.jay.udacitypopularmovies.service.MoviesService;
import com.example.jay.udacitypopularmovies.service.MoviesServiceImpl;

/**
 * Created by Jay on 2017-07-16.
 */

public class Injector {

    public static MoviesService provideMoviesService(){
        return new MoviesServiceImpl();
    }

    public static DatabaseService provideDatabaseService(){
        return new DatabaseServiceImpl();
    }
}
