package com.example.jay.udacitypopularmovies.ui.activity.mainactivity;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.jay.udacitypopularmovies.R;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();
    private boolean mTwoPane;

    private static final String FRAGMENT_MOVIE_TAG = "fragment_movie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTwoPane = findViewById(R.id.details_container) != null;
    }

}
