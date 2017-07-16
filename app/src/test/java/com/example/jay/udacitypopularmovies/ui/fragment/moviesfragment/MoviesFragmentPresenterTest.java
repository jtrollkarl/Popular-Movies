package com.example.jay.udacitypopularmovies.ui.fragment.moviesfragment;

import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.MoviesService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

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


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MoviesFragmentPresenter(moviesService, databaseService);
    }

    @Test
    public void fetchMovies() throws Exception {
        presenter.fetchMovies();
        verify(moviesService, times(1)).fetchMovies();
    }


    @Test
    public void onClickMovie() throws Exception {
        presenter.onClickMovie(new Movie());
        verify(view, times(1)).showMovieDetails(any(Movie.class));
    }

}