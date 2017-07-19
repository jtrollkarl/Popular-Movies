package com.example.jay.udacitypopularmovies.schedulers;

import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Jay on 2017-07-19.
 */

public class ImmediateSchedulers implements BaseSchedulerProvider {

    @Override
    public Scheduler computation() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler io() {
        return Schedulers.trampoline();
    }

    @Override
    public Scheduler ui() {
        return Schedulers.trampoline();
    }
}
