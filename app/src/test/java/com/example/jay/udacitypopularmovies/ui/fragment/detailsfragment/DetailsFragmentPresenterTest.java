package com.example.jay.udacitypopularmovies.ui.fragment.detailsfragment;

import com.example.jay.udacitypopularmovies.R;

import com.example.jay.udacitypopularmovies.data.model.Movie;
import com.example.jay.udacitypopularmovies.data.model.ResultReviews;
import com.example.jay.udacitypopularmovies.data.model.ResultTrailer;

import com.example.jay.udacitypopularmovies.data.model.Review;
import com.example.jay.udacitypopularmovies.data.model.Trailer;
import com.example.jay.udacitypopularmovies.retrofitservice.RetrofitService;
import com.example.jay.udacitypopularmovies.schedulers.BaseSchedulerProvider;
import com.example.jay.udacitypopularmovies.schedulers.ImmediateSchedulers;
import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.MoviesService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.Result;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jay on 2017-07-22.
 */
public class DetailsFragmentPresenterTest {

    @Mock
    DetailsFragmentContract.View view;

    @Mock
    private RetrofitService moviesService;

    @Mock
    private DatabaseService databaseService;

    private DetailsFragmentPresenter detailsFragmentPresenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        BaseSchedulerProvider schedulerProvider = new ImmediateSchedulers();
        detailsFragmentPresenter = new DetailsFragmentPresenter(moviesService, databaseService, schedulerProvider);
        detailsFragmentPresenter.attachView(view);
    }

    @Test
    public void loadMovie() throws Exception{
        Movie fake = Movie.getFakes(1).get(0);

        detailsFragmentPresenter.loadMovie(fake);
        verify(view, times(1)).showMovie(fake);
    }

    @Test
    public void fetchReviews_SUCCESS() throws Exception {

        when(moviesService.fetchReviews(anyString(), anyString())).thenReturn(Single.just(mock(Review.class)));

        detailsFragmentPresenter.fetchReviews("1");

        verify(view, times(1)).showReviews(ArgumentMatchers.<ResultReviews>anyList());
    }

    @Test
    public void fetchReviews_ERROR() throws Exception{
        when(moviesService.fetchReviews(anyString(), anyString())).thenReturn(Single.<Review>error(new Throwable("Connection error")));

        detailsFragmentPresenter.fetchReviews("1");

        verify(view, times(1)).showMessage(R.string.error_fetch_reviews);
    }

    @Test
    public void fetchTrailers_SUCCESS() throws Exception{
        when(moviesService.fetchTrailers(anyString(), anyString())).thenReturn(Single.just(mock(Trailer.class)));

        detailsFragmentPresenter.fetchTrailers("");

        verify(view, times(1)).showTrailers(ArgumentMatchers.<ResultTrailer>anyList());
    }

    @Test
    public void fetchTrailers_ERROR() throws Exception{
        when(moviesService.fetchTrailers(anyString(), anyString())).thenReturn(Single.<Trailer>error(new Throwable("Connection error")));

        detailsFragmentPresenter.fetchTrailers("1");

        verify(view, times(1)).showMessage(R.string.error_fetch_trailers);
    }

    @Test
    public void saveFavourite_SUCCESS() throws Exception{
        when(databaseService.saveFavourite(any(Movie.class))).thenReturn(Completable.complete());

        detailsFragmentPresenter.onClickFavourite(new Movie());

        verify(view, times(1)).showMessage(R.string.success_save_movie);
    }

    @Test
    public void saveFavourite_ERROR() throws Exception{
        when(databaseService.saveFavourite(any(Movie.class))).thenReturn(Completable.error(new Throwable("Database error")));

        detailsFragmentPresenter.onClickFavourite(new Movie());

        verify(view, times(1)).showMessage(R.string.error_save_favourite);
    }
}