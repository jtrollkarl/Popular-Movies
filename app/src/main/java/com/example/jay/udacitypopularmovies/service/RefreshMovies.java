package com.example.jay.udacitypopularmovies.service;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import com.example.jay.udacitypopularmovies.retrofitservice.PopularMoviesService;
import com.example.jay.udacitypopularmovies.apikey.MovieApiKey;
import com.example.jay.udacitypopularmovies.dbandmodels.DatabaseStorageRetrieval;
import com.example.jay.udacitypopularmovies.dbandmodels.Movie;
import com.example.jay.udacitypopularmovies.dbandmodels.Page;
import com.example.jay.udacitypopularmovies.misc.Urls;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
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


    private Retrofit retrofit;

    public RefreshMovies() {
        super("RefreshMovies");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_SWITCH_POPULAR.equals(action)) {
                handleMoviesAsync(Page.POPULAR);
            } else if (ACTION_APP_START.equals(action)) {
                handleMoviesAsync(Page.POPULAR);
            } else if (ACTION_SWTICH_TOP_RATED.equals(action)){
                handleMoviesAsync(Page.TOP_RATED);
            }
        }
    }


    private void handleMoviesAsync(String action){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(Urls.TMDB_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();



        PopularMoviesService service = retrofit.create(PopularMoviesService.class);
        Call<Page> movies = service.listMovies(action, MovieApiKey.ApiKey);
        Log.d(TAG, "Request is: " + movies.request().url());

        movies.enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if(response.isSuccessful()){
                    if(response.errorBody() != null){
                        try {
                            Log.d(TAG, response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    ArrayList<Movie> listofmovies = (ArrayList<Movie>) response.body().getResults();
                    DatabaseStorageRetrieval.insert(RefreshMovies.this, listofmovies);
                }else{
                    //somerhing went wrong
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {

                Log.d(TAG, t.getMessage());
            }
        });
    }
}
