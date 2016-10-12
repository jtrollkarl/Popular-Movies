package com.example.jay.udacitypopularmovies;

import android.content.Context;
import android.database.Cursor;
import android.media.Image;
import android.support.v4.widget.CursorAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.jay.udacitypopularmovies.adapters.CursorRecyclerViewAdapter;
import com.squareup.picasso.Picasso;

/**
 * Created by Jay on 2016-10-11.
 */

public class MovieAdapter extends CursorRecyclerViewAdapter<MovieAdapter.ViewHolder>{

    public MovieAdapter(Context context, Cursor cursor) {
        super(context, cursor);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView poster;
        public ViewHolder(View view){
            super(view);
            poster = (ImageView) view.findViewById(R.id.poster);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Cursor cursor) {
        String posterPath = cursor.getString(cursor.getColumnIndexOrThrow("poster_path"));
        Picasso.with(super.getContext()).load("http://image.tmdb.org/t/p/w185//"+ posterPath).into(viewHolder.poster);
    }
}
