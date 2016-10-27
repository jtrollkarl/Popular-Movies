package com.example.jay.udacitypopularmovies.adapters;

import android.content.ContentProvider;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.ResultReviews;
import com.example.jay.udacitypopularmovies.ResultTrailer;
import com.example.jay.udacitypopularmovies.Trailer;
import com.example.jay.udacitypopularmovies.Urls;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Jay on 2016-10-27.
 */

public class TrailerAdapter extends RecyclerView.Adapter<TrailerAdapter.MovieViewHolder> {

    private ArrayList<ResultTrailer> trailers = new ArrayList<ResultTrailer>();
    private Context context;
    LayoutInflater inflater;
    private static final String TAG = TrailerAdapter.class.getSimpleName();

    public TrailerAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = inflater.inflate(R.layout.trailer_row, parent, false);
            return new MovieViewHolder(v);
    }

    public void setTrailers(ArrayList<ResultTrailer> trailers){
        this.trailers = trailers;
        notifyDataSetChanged();
    }


    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final ResultTrailer trailer = trailers.get(position);
        TextView trailerTitle = holder.trailerTitle;
        trailerTitle.setText(trailer.getName());
        Picasso.with(context).load(Urls.YOUTUBE_THUMBNAIL_BASE +trailer.getKey() + Urls.YOUTUBE_THUMBNAIL_QUALITY_MED).into(holder.trailerImage);

        holder.trailerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(Urls.YOUTUBE_VIDEO_BASE + trailer.getKey())));
            }
        });

    }

    @Override
    public int getItemCount() {
        return trailers.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder{

        TextView trailerTitle;
        ImageView trailerImage;

        public MovieViewHolder(View itemView) {
            super(itemView);
            trailerTitle = (TextView) itemView.findViewById(R.id.trailer_title);
            trailerImage = (ImageView) itemView.findViewById(R.id.trailer_image);
        }


    }
}