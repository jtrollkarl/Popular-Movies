package com.example.jay.udacitypopularmovies.activities;

import android.graphics.Color;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jay.udacitypopularmovies.Movie;
import com.example.jay.udacitypopularmovies.R;
import com.example.jay.udacitypopularmovies.fragments.DetailFragment;
import com.example.jay.udacitypopularmovies.fragments.MovieFragment;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;



public class DetailActivity extends AppCompatActivity {


    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbarLayout;

    public static final String TAG = DetailActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Movie movie = getIntent().getParcelableExtra("movie");
        DetailFragment detailFragment = (DetailFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_detail);
        detailFragment.loadMovie(movie);


        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        //toolbar.inflateMenu(R.menu.menu_detail);
        setSupportActionBar(toolbar);
        //getSupportActionBar().hide();

        //loadMovie(movie);
        collapsingToolbarLayout.setExpandedTitleColor(Color.TRANSPARENT);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        toolbar.inflateMenu(R.menu.menu_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }






}
