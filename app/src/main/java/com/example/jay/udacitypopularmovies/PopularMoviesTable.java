package com.example.jay.udacitypopularmovies;

import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.annotation.Unique;
import com.raizlabs.android.dbflow.structure.BaseModel;

/**
 * Created by Jay on 2016-10-08.
 */
//@Table(database = PopularMoviesDatabase.class)
public class PopularMoviesTable extends BaseModel {

    @PrimaryKey(autoincrement = true)
    long id;

    @Column
    @Unique
    String title;

    @Column
    @Unique
    String description;

    @Column
    @Unique
    String posterPath;

    @Column
    @Unique
    Double voteAverage;

    @Column
    @Unique
    Double votePopularity;

}
