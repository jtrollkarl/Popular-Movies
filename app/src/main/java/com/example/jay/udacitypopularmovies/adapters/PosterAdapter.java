package com.example.jay.udacitypopularmovies.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;


import com.example.jay.udacitypopularmovies.R;

import java.util.List;

/**
 * Created by Jay on 2016-10-06.
 */

public class PosterAdapter extends ArrayAdapter {
    public PosterAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }


    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View posterRow = inflater.inflate(R.layout.row, parent, false);





        return posterRow;
    }
}
