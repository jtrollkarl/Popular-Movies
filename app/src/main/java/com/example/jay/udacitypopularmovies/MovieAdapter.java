package com.example.jay.udacitypopularmovies;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

    public static final String TAG = MovieAdapter.class.getSimpleName();


    private MovieSelectedListener listener;

    public interface MovieSelectedListener{
        void onMovieSelected(Movie movie);
    }

    public MovieAdapter(Context context, Cursor cursor) {
        super(context, cursor);
        listener = (MovieSelectedListener) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater
                .from(parent.getContext()).inflate(R.layout.row_cardview, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final Cursor cursor) {
        String posterPath = cursor.getString(cursor.getColumnIndexOrThrow("posterPath"));
        CardView cardView = viewHolder.poster;
        ImageView img = (ImageView) cardView.findViewById(R.id.card_movie_poster);
        Picasso.with(super.getContext()).load("http://image.tmdb.org/t/p/w185//"+ posterPath).into(img);


        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cursor.moveToPosition(viewHolder.getLayoutPosition());
                listener.onMovieSelected(Utils.cursorToMovie(cursor));
            }
        });
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public CardView poster;
        public ViewHolder(View view){
            super(view);
            poster = (CardView) view.findViewById(R.id.card_movie);
        }
    }
}
