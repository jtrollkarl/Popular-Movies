package com.example.jay.udacitypopularmovies.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.data.model.ResultReviews;

import java.util.List;

/**
 * Created by Jay on 2016-10-28.
 */

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.Viewholder> {

    private List<ResultReviews> reviews;
    private Context mContext;
    private LayoutInflater inflater;
    private static final String TAG = TrailerAdapter.class.getSimpleName();

    public ReviewAdapter(Context context) {
        this.mContext = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ReviewAdapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.review_row, parent, false);
        return new Viewholder(v);
    }

    @Override
    public void onBindViewHolder(ReviewAdapter.Viewholder holder, int position) {
        ResultReviews review = reviews.get(position);
        TextView author = holder.author;
        TextView content = holder.content;
        author.setText(review.getAuthor());
        content.setText(review.getContent());

    }

    @Override
    public int getItemCount() {
        if(reviews == null){
            return 0;
        }else {
            return reviews.size();
        }
    }

    public void setReviews(List<ResultReviews> reviews){
        this.reviews = reviews;
        notifyDataSetChanged();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        TextView author;
        TextView content;

        public Viewholder(View itemView) {
            super(itemView);
            author = (TextView) itemView.findViewById(R.id.review_author);
            content = (TextView) itemView.findViewById(R.id.review_content);
        }
    }
}
