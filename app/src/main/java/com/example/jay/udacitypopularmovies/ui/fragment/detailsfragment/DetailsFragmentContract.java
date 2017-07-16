package com.example.jay.udacitypopularmovies.ui.fragment.detailsfragment;

import android.support.annotation.StringRes;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.hannesdorfmann.mosby3.mvp.MvpPresenter;
import com.hannesdorfmann.mosby3.mvp.MvpView;

/**
 * Created by Jay on 2017-07-16.
 */

public interface DetailsFragmentContract {

    interface View extends MvpView {
        void showMovieDetails(Movie movie);
        void showMessage(@StringRes int resId);
    }

    interface Actions extends MvpPresenter<View>{
        void onClickFavourite(Movie movie);
    }

}
