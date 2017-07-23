package com.example.jay.udacitypopularmovies.ui.fragment.detailsfragment;

import android.support.annotation.StringRes;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultReviews;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultTrailer;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

import java.util.List;

/**
 * Created by Jay on 2017-07-16.
 */

public interface DetailsFragmentContract {

    interface View extends MvpView {
        void showMovie(Movie movie);
        void showReviews(List<ResultReviews> reviews);
        void showTrailers(List<ResultTrailer> trailers);
        void showMessage(@StringRes int resId);
    }

    interface Actions extends MvpPresenter<View>{
        void loadMovie(Movie movie);
        void fetchReviews(String movieId);
        void fetchTrailers(String movieId);
        void onClickFavourite(Movie favouriteMovie);
        void onPause();
    }

}
