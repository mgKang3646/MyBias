package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MovieDetailViewAdapter;
import com.example.actorsearchapplication.models.MovieDetailModel;

public class MovieDetailObserver implements Observer<MovieDetailModel> {

    private MovieDetailViewAdapter movieDetailViewAdapter;

    public MovieDetailObserver(MovieDetailViewAdapter movieDetailViewAdapter){
        this.movieDetailViewAdapter = movieDetailViewAdapter;
    }

    @Override
    public void onChanged(MovieDetailModel movieDetailModel) {
        if(movieDetailModel != null){
            movieDetailViewAdapter.setModel(movieDetailModel);
            movieDetailViewAdapter.onBind();
        }else{
            Log.v("Tag","MovieDetailObserver : movieDetailModel == null");
        }
    }
}
