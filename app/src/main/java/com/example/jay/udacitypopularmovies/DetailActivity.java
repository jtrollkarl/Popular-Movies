package com.example.jay.udacitypopularmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.movieName) TextView movieText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        ArrayList<Movie> list = getIntent().getParcelableArrayListExtra("movie");
        Movie movie = list.get(0);

        movieText.setText(movie.getTitle());



    }




}
