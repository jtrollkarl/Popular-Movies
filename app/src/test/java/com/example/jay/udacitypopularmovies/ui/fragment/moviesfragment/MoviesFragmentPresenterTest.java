package com.example.jay.udacitypopularmovies.ui.fragment.moviesfragment;

import com.example.jay.udacitypopularmovies.service.DatabaseService;
import com.example.jay.udacitypopularmovies.service.MoviesService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;

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

    }

    @Test
    public void fetchMovies() throws Exception {

    }

    @Test
    public void fetchMovies1() throws Exception {

    }

    @Test
    public void onClickMovie() throws Exception {

    }

}