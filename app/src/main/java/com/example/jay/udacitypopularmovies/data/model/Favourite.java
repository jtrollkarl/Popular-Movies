package com.example.jay.udacitypopularmovies.data.model;

import com.example.jay.udacitypopularmovies.data.database.PopularMoviesDatabase;
import com.raizlabs.android.dbflow.annotation.Table;

/**
 * Created by Jay on 2017-07-24.
 */

@Table(database = PopularMoviesDatabase.class, cachingEnabled = true)
public class Favourite extends Movie {

    /*
    I know this is messy. It would have been better to create an abstract super class (for movie)
    and have 2 different subclasses (FetchedMovie and Favourite)
    */

    public Favourite() {
    }

    public Favourite(Movie movie){
        super.setAdult(movie.isAdult());
        super.setBackdropPath(movie.getBackdropPath());
        super.setGenreIds(movie.getGenreIds());
        super.setId(movie.getId());
        super.setOriginalLanguage(movie.getOriginalLanguage());
        super.setOriginalTitle(movie.getOriginalTitle());
        super.setOverview(movie.getOverview());
        super.setPopularity(movie.getPopularity());
        super.setPosterPath(movie.getPosterPath());
        super.setReleaseDate(movie.getReleaseDate());
        super.setTitle(movie.getTitle());
        super.setVideo(movie.isVideo());
        super.setVoteAverage(movie.getVoteAverage());
        super.setVoteCount(movie.getVoteCount());
    }
}
