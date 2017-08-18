package com.example.jay.udacitypopularmovies.ui.fragment.moviesfragment;

import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;

import com.example.jay.udacitypopularmovies.dbandmodels.Page;
import com.example.jay.udacitypopularmovies.retrofitservice.RetrofitService;
import com.example.jay.udacitypopularmovies.schedulers.ImmediateSchedulers;
import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.MoviesService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Jay on 2017-07-16.
 */
public class MoviesFragmentPresenterTest {

    @Mock
    private MovieFragmentContract.View view;

    @Mock
    private DatabaseService databaseService;

    @Mock
    private RetrofitService moviesService;

    private MoviesFragmentPresenter presenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MoviesFragmentPresenter(moviesService, databaseService, new ImmediateSchedulers());
        presenter.attachView(view);
    }

    @Test
    public void fetchMovies_SUCCESS() throws Exception {

        when(moviesService.fetchMovies(anyString(), anyInt(), anyString())).thenReturn(Single.just(mock(Page.class)));
        when(databaseService.insertMovies(ArgumentMatchers.<Movie>anyList())).thenReturn(Completable.complete());

        presenter.fetchMovies(1);

        verify(view, times(1)).showMovies();
    }

    @Test
    public void fetchMovies_ERROR() throws Exception {
        when(moviesService.fetchMovies(anyString(), anyInt(), anyString())).thenReturn(Single.<Page>error(new Throwable("Connection error")));
        presenter.fetchMovies(1);

        verify(view, times(1)).showMovies();
        verify(view, times(1)).showMessage(R.string.error_fetch_movies);
    }

    @Test
    public void fetchMovies_DATABASE_ERROR() throws Exception {
        when(moviesService.fetchMovies(anyString(), anyInt(), anyString())).thenReturn(Single.just(mock(Page.class)));
        when(databaseService.insertMovies(ArgumentMatchers.<Movie>anyList())).thenReturn(Completable.error(new Throwable("Database error")));

        presenter.fetchMovies(1);

        verify(view, times(1)).showMessage(R.string.error_insert_movies);
    }

    @Test
    public void onClickMovie() throws Exception {
        presenter.onClickMovie(new Movie());
        verify(view, times(1)).showMovieDetails(any(Movie.class));
    }

    @Test
    public void onClickSortFavourites() throws Exception{
        presenter.onClickSortFavourites();
        verify(view).showMovies();
    }

    @Test
    public void onClickSortPopular() throws Exception{
        when(moviesService.fetchMovies(anyString(), anyInt(), anyString())).thenReturn(Single.just(mock(Page.class)));
        when(databaseService.insertMovies(ArgumentMatchers.<Movie>anyList())).thenReturn(Completable.complete());

        presenter.onClickSortPopular();
        verify(moviesService, times(1)).fetchMovies(anyString(), anyInt(), anyString());
    }

    @Test
    public void onClickSortTopRated() throws Exception{
        when(moviesService.fetchMovies(anyString(), anyInt(), anyString())).thenReturn(Single.just(mock(Page.class)));
        when(databaseService.insertMovies(ArgumentMatchers.<Movie>anyList())).thenReturn(Completable.complete());

        presenter.onClickSortTopRated();
        verify(moviesService, times(1)).fetchMovies(anyString(), anyInt(), anyString());
    }

}