package com.example.jay.udacitypopularmovies;

import com.example.jay.udacitypopularmovies.retrofitservice.RetrofitFactory;
import com.example.jay.udacitypopularmovies.retrofitservice.RetrofitService;
import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.DatabaseServiceImpl;

/**
 * Created by Jay on 2017-07-16.
 */

public class Injector {

    public static RetrofitService provideMoviesService(){
        return RetrofitFactory.createRetrofit(RetrofitService.class);
    }

    public static DatabaseService provideDatabaseService(){
        return new DatabaseServiceImpl();
    }
}
