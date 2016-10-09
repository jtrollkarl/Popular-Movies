package com.example.jay.udacitypopularmovies;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 *
 */
public class RefreshMovies extends IntentService {

    private static final String TAG = RefreshMovies.class.getSimpleName();
    public static final String ACTION_APP_START = "com.example.jay.udacitypopularmovies.action.APPLICATION_START";
    public static final String ACTION_SWITCH_POPULAR = "com.example.jay.udacitypopularmovies.action.SWITCH_POPULAR";
    public static final String ACTION_SWTICH_TOP_RATED = "com.example.jay.udacitypopularmovies.action.SWITCH_TOP_RATED";

    // auto generated constants
    public static final String EXTRA_PARAM1 = "com.example.jay.udacitypopularmovies.extra.PARAM1";
    public static final String EXTRA_PARAM2 = "com.example.jay.udacitypopularmovies.extra.PARAM2";

    private Retrofit retrofit;

    public RefreshMovies() {
        super("RefreshMovies");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SWITCH_POPULAR.equals(action)) {
/*                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);*/
                handleRefresh();
            } else if (ACTION_APP_START.equals(action)) {
/*                final String param1 = intent.getStringExtra(EXTRA_PARAM1);
                final String param2 = intent.getStringExtra(EXTRA_PARAM2);*/
                handleAppStart();
            } else if (ACTION_SWTICH_TOP_RATED.equals(action)){

            }
        }
    }

    private void handleRefresh() {
        if(retrofit != null){
            //refresh movies
        }

    }

    private void handleAppStart() {
        Log.d(TAG, "Starting app");
        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        PopularMoviesService service = retrofit.create(PopularMoviesService.class);
        Call<Page> movies = service.listMovies(Page.POPULAR);
        ArrayList<Movie> results;

        try {
            results = (ArrayList<Movie>) movies.execute().body().getMovies();
            DatabaseStorageRetrieval db = new DatabaseStorageRetrieval();
            db.insert(results);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
