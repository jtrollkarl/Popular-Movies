package com.example.jay.udacitypopularmovies.fragments;


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
import android.view.View;
import android.view.ViewGroup;

import com.example.jay.udacitypopularmovies.MovieAdapter;
import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.RecyclerViewItemDecorator;
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

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);
        //layout manager
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        //end layoutmanager
        //RecyclerView recyclerView = (RecyclerView) getView().findViewById(R.id.movie_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MovieAdapter(getActivity(), null);
        recyclerView.addItemDecoration(new RecyclerViewItemDecorator(10));
        recyclerView.setAdapter(adapter);


        getActivity().getSupportLoaderManager().initLoader(1, null, this);
    }



    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        Log.d(TAG, "Creating loader");
        return new UILoader(getActivity().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        Log.d(TAG, "Load finished");
        //Log.d(TAG, cursor.getString(cursor.getColumnIndexOrThrow("title")));
        adapter.swapCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        Log.d(TAG, "Loader reset");
        adapter.swapCursor(null);
    }




}
