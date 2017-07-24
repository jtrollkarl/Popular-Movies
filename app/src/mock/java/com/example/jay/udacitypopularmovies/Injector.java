package com.example.jay.udacitypopularmovies;

import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.DatabaseServiceImpl;
import com.example.jay.udacitypopularmovies.service.FakeMoviesService;
import com.example.jay.udacitypopularmovies.service.MoviesService;

/**
 * Created by Jay on 2017-07-16.
 */

public class Injector {

    public static MoviesService provideMoviesService(){
        return new FakeMoviesService();
    }

    public static DatabaseService provideDatabaseService(){
        return new DatabaseServiceImpl();
    }

}
