package com.example.jay.udacitypopularmovies.fragments;


import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.udacitypopularmovies.adapters.MovieAdapter;
import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.RecyclerViewItemDecorator;
import com.example.jay.udacitypopularmovies.RefreshMovies;
import com.example.jay.udacitypopularmovies.UILoader;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    public static final String TAG = MovieFragment.class.getSimpleName();
    @BindView(R.id.movie_recycler) RecyclerView recyclerView;
    private MovieAdapter adapter;
    public static int CURRENT_LOADER;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        //layout manager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        //end layoutmanager

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(getActivity(), null);
        recyclerView.addItemDecoration(new RecyclerViewItemDecorator(10));
        recyclerView.setAdapter(adapter);

        CURRENT_LOADER = 1;
        getActivity().getSupportLoaderManager().initLoader(1, null, this);
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() ==  R.id.menuSortPopularity) {
            Intent popularIntent = new Intent(getActivity(), RefreshMovies.class);
            popularIntent.setAction(RefreshMovies.ACTION_SWITCH_POPULAR);
            getActivity().startService(popularIntent);
            getActivity().getSupportLoaderManager().initLoader(1, null, this);
            refreshLoader(1);
            return true;
        }
        else if(item.getItemId() == R.id.menuSortRating){
            Intent topRatedIntent = new Intent(getActivity(), RefreshMovies.class);
            topRatedIntent.setAction(RefreshMovies.ACTION_SWTICH_TOP_RATED);
            getActivity().startService(topRatedIntent);
            getActivity().getSupportLoaderManager().initLoader(2, null, this);
            refreshLoader(2);
            return true;
        }
        else if(item.getItemId() == R.id.menuSortFavourites){
            getActivity().getSupportLoaderManager().initLoader(3, null, this);
            refreshLoader(3);
            return true;

        }
        return true;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        switch (id){
            case 1:
                return new UILoader(1, getActivity().getApplicationContext());
            case 2:
                return new UILoader(2, getActivity().getApplicationContext());
            case 3:
                return new UILoader(3, getActivity().getApplicationContext());
            default:
                return new UILoader(1, getActivity().getApplicationContext());
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "Load finished");
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "Loader reset");
        adapter.swapCursor(null);
    }

    private void refreshLoader(int id){
        if(id != CURRENT_LOADER){
            getActivity().getSupportLoaderManager().destroyLoader(CURRENT_LOADER);
            CURRENT_LOADER = id;
        }else{
            //Do nothing
        }

    }


}
