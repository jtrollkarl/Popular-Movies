package com.example.jay.udacitypopularmovies.ui.fragment.moviesfragment;

import android.support.annotation.StringRes;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

/**
 * Created by Jay on 2017-07-16.
 */

public interface MovieFragmentContract {

    interface View extends MvpView {
        void showMovies(List<Movie> movies);

        void showMovieDetails(Movie movie);

        void showMessage(@StringRes int resId);

        void showLoading();

        void showNotLoading();

    }

    interface Actions extends MvpPresenter<View> {
        void fetchMovies();

        void fetchMovies(int pageNumber);

        void onClickMovie(Movie movie);

        void onClickSortPopular();

        void onClickSortTopRated();

        void onClickSortFavourites();
    }
}
