package com.example.jay.udacitypopularmovies.ui.fragment.detailsfragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jay.udacitypopularmovies.Injector;
import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.adapters.ReviewAdapter;
import com.example.jay.udacitypopularmovies.adapters.TrailerAdapter;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultReviews;
import com.example.jay.udacitypopularmovies.dbandmodels.ResultTrailer;
import com.example.jay.udacitypopularmovies.misc.Urls;
import com.example.jay.udacitypopularmovies.schedulers.SchedulerProvider;
import com.example.jay.udacitypopularmovies.service.DatabaseServiceImpl;
import com.hannesdorfmann.mosby3.mvp.MvpFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;



public class DetailFragment extends MvpFragment<DetailsFragmentContract.View, DetailsFragmentContract.Actions> implements DetailsFragmentContract.View {


    public static final String TAG = DetailFragment.class.getSimpleName();
    public static final String MOVIE_KEY = "MOVIE_KEY";

    @BindView(R.id.poster)
    ImageView poster;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.collapsingToolbar)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView(R.id.detailMovie)
    ImageView detailMovie;
    @BindView(R.id.movieTitle)
    TextView movieTitle;
    @BindView(R.id.movieRelease)
    TextView movieRelease;
    @BindView(R.id.movieRating)
    TextView movieRating;
    @BindView(R.id.movieSynopsis)
    TextView movieSynopsis;
    @BindView(R.id.fabFavourite)
    FloatingActionButton fabFavourite;
    @BindView(R.id.trailer_recycler)
    RecyclerView trailerRecycler;
    @BindView(R.id.review_recycler)
    RecyclerView reviewRecycler;

    private TrailerAdapter trailerAdapter;
    private ReviewAdapter reviewAdapter;
    private Movie movieCurrent;
    private Unbinder unbinder;


    @Override
    public DetailsFragmentContract.Actions createPresenter() {
        return new DetailsFragmentPresenter(Injector.provideMoviesService(), new DatabaseServiceImpl(), new SchedulerProvider());
    }

    public static DetailFragment newInstance(Movie movie) {
        DetailFragment detailFragment = new DetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie_key", movie);
        detailFragment.setArguments(args);

        return detailFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);

        LinearLayoutManager lmH = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        trailerAdapter = new TrailerAdapter(getContext());
        trailerRecycler.setLayoutManager(lmH);
        trailerRecycler.setAdapter(trailerAdapter);

        LinearLayoutManager lmV = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        reviewAdapter = new ReviewAdapter(getContext());
        reviewRecycler.setLayoutManager(lmV);
        reviewRecycler.setAdapter(reviewAdapter);


        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments().containsKey("movie_key")) {
            movieCurrent = getArguments().getParcelable("movie_key");
            presenter.loadMovie((Movie) getArguments().getParcelable("movie_key"));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        if (movieCurrent != null) {
            outState.putParcelable(MOVIE_KEY, movieCurrent);
        }
        super.onSaveInstanceState(outState);
    }


    @Override
    public void showMovie(Movie movie) {
        this.movieCurrent = movie;

        movieSynopsis.setText(movie.getOverview());
        movieRating.setText(String.valueOf(movie.getVoteAverage()));
        movieTitle.setText(movie.getTitle());
        movieRelease.setText(movie.getReleaseDate());

        Log.d(TAG, "loadMovie called");
        Picasso.with(getActivity())
                .load(Urls.TMDB_BACKDROP_IMG_URL + movie.getBackdropPath())
                .error(R.drawable.reggie_head)
                .into(poster);

        Picasso.with(getActivity())
                .load(Urls.TMDB_POSTER_IMG_URL + movie.getPosterPath())
                .error(R.drawable.reggie_head)
                .into(detailMovie);

        presenter.fetchReviews(String.valueOf(movie.getId()));
        presenter.fetchTrailers(String.valueOf(movie.getId()));
    }

    @Override
    public void showReviews(List<ResultReviews> reviews) {
        reviewAdapter.setReviews(reviews);
    }

    @Override
    public void showTrailers(List<ResultTrailer> trailers) {
        trailerAdapter.setTrailers(trailers);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        Toast.makeText(getActivity(), resId, Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.fabFavourite)
    public void onViewClicked() {
        presenter.onClickFavourite(movieCurrent);
    }

    @Override
    public void onPause() {
        super.onPause();
        presenter.onPause();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }



}
