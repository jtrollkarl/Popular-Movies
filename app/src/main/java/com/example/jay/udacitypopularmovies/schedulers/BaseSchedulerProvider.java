package com.example.jay.udacitypopularmovies.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.annotations.NonNull;

/**
 * Created by Jay on 2017-07-19.
 */

public interface BaseSchedulerProvider {

    @NonNull
    Scheduler computation();

    @NonNull
    Scheduler io();

    @NonNull
    Scheduler ui();

}
