package com.example.jay.udacitypopularmovies;

import android.database.Cursor;

import java.util.ArrayList;

/**
 * Created by Jay on 2016-10-13.
 */

public class Utils {

    public static Movie cursorToMovie(Cursor c){

/*      * @param id
        * @param genreIds
        * @param title
        * @param releaseDate
        * @param overview
        * @param posterPath
        * @param originalTitle
        * @param voteAverage
        * @param originalLanguage
        * @param adult
        * @param backdropPath
        * @param voteCount
        * @param video
        * @param popularity*/

        Movie movie = new Movie();
        System.out.println(c.getColumnIndexOrThrow("id"));
        movie.setId(Integer.valueOf(c.getString(c.getColumnIndexOrThrow("id"))));
        movie.setBackdropPath(c.getString(c.getColumnIndexOrThrow("backdropPath")));
        movie.setTitle(c.getString(c.getColumnIndexOrThrow("title")));
        movie.setReleaseDate(c.getString(c.getColumnIndexOrThrow("releaseDate")));
        movie.setOverview(c.getString(c.getColumnIndexOrThrow("overview")));
        movie.setPosterPath(c.getString(c.getColumnIndexOrThrow("posterPath")));
        movie.setOriginalTitle(c.getString(c.getColumnIndexOrThrow("originalTitle")));
        movie.setVoteAverage(Double.parseDouble(c.getString(c.getColumnIndexOrThrow("voteAverage"))));
        movie.setVoteCount(Integer.valueOf(c.getString(c.getColumnIndexOrThrow("voteCount"))));
        movie.setPopularity(Double.valueOf(c.getString(c.getColumnIndexOrThrow("popularity"))));

        return movie;
    }

}
