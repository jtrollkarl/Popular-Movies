package com.example.jay.udacitypopularmovies;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.jay.udacitypopularmovies.apikey.MovieApiKey;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import info.movito.themoviedbapi.TmdbApi;
import info.movito.themoviedbapi.TmdbMovies;
import info.movito.themoviedbapi.model.MovieDb;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Response;
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

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl("http://api.themoviedb.org")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        PopularMoviesService service = retrofit.create(PopularMoviesService.class);
        Call<Page> movies = service.listMovies(Page.POPULAR, MovieApiKey.ApiKey);
        Log.d(TAG, "Request is: " + movies.request().url());
        //ArrayList<Movie> resultsList;

        TmdbMovies moviesList = new TmdbApi(MovieApiKey.ApiKey).getMovies();

        Log.d(TAG, moviesList.getPopularMovies("", 1).getResults().toString());

        try {
            Response<Page> response = movies.execute();
            if(response.errorBody() != null){
                Log.d(TAG, response.errorBody().string());
            }
            //List<Movie> listt = gson.fromJson("results", Movie.class);
            Log.d(TAG, "Request is: "+ String.valueOf(response.isSuccessful()));
            Log.d(TAG, String.valueOf(response.body().getTotalResults()));
            ArrayList<Movie> listofmovies = (ArrayList<Movie>) response.body().getResults();
            System.out.println(listofmovies.size());
            listofmovies.get(0).getOriginalLanguage();
            DatabaseStorageRetrieval db = new DatabaseStorageRetrieval();
            db.insert(listofmovies);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
