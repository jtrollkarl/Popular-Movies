package com.example.jay.udacitypopularmovies.ui.fragment.moviesfragment;

import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.schedulers.BaseSchedulerProvider;
import com.example.jay.udacitypopularmovies.schedulers.ImmediateSchedulers;
import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.MoviesService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOError;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
    private MoviesService moviesService;

    private MoviesFragmentPresenter presenter;

    private BaseSchedulerProvider schedulerProvider;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        schedulerProvider = new ImmediateSchedulers();
        presenter = new MoviesFragmentPresenter(moviesService, databaseService, new ImmediateSchedulers());
        presenter.attachView(view);
    }

    @Test
    public void fetchMovies_SUCCESS() throws Exception {
        List<Movie> fakes = Movie.getFakes(5);

        when(moviesService.fetchMovies()).thenReturn(Single.just(fakes));
        when(databaseService.insertMovies(ArgumentMatchers.<Movie>anyList())).thenReturn(Completable.complete());

        presenter.fetchMovies();

        verify(view, times(1)).showMovies();
    }

    @Test
    public void fetchMovies_ERROR() throws Exception {
        when(moviesService.fetchMovies()).thenReturn(Single.<List<Movie>>error(new Throwable("Connection error")));
        presenter.fetchMovies();

        verify(view, times(1)).showMessage(R.string.error_fetch_movies);
    }

    @Test
    public void fetchMovies_PAGE_SUCCESS() throws Exception {
        List<Movie> fakes = Movie.getFakes(5);

        when(moviesService.fetchMoviesPage(anyInt())).thenReturn(Single.just(fakes));
        when(databaseService.insertMovies(ArgumentMatchers.<Movie>anyList())).thenReturn(Completable.complete());

        presenter.fetchMovies(5);

        verify(view, times(1)).showMovies();
    }

    @Test
    public void fetchMovies_DATABASE_ERROR() throws Exception {
        List<Movie> fakes = Movie.getFakes(5);

        when(moviesService.fetchMovies()).thenReturn(Single.just(fakes));
        when(databaseService.insertMovies(ArgumentMatchers.<Movie>anyList())).thenReturn(Completable.error(new Throwable("Database error")));

        presenter.fetchMovies();

        verify(view, times(1)).showMessage(R.string.error_insert_movies);
    }


    @Test
    public void onClickMovie() throws Exception {
        presenter.onClickMovie(new Movie());
        verify(view, times(1)).showMovieDetails(any(Movie.class));
    }

}