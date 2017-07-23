package com.example.jay.udacitypopularmovies.misc;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Jay on 2017-07-23.
 */

public class RecyclerScrollListener extends EndlessRecyclerScrollListener {

    private OnLoadMoreListener listener;

    public interface OnLoadMoreListener{
        void loadMore(int pageNumber);
    }

    public RecyclerScrollListener(GridLayoutManager layoutManager, OnLoadMoreListener listener) {
        super(layoutManager);
        this.listener = listener;
    }

    @Override
    public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
        listener.loadMore(page);

    }
}
