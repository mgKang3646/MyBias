package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.adapters.MovieDetailViewAdapter;
import com.example.actorsearchapplication.models.MovieDetailModel;

public class MovieDetailObserver implements Observer<MovieDetailModel> {

    private MainViewAdapter mainViewAdapter;

    public MovieDetailObserver(MainViewAdapter mainViewAdapter){
        this.mainViewAdapter = mainViewAdapter;
    }

    @Override
    public void onChanged(MovieDetailModel movieDetailModel) {
        if(movieDetailModel != null){
            mainViewAdapter.setModel(movieDetailModel);
            mainViewAdapter.onBind();
        }else{
            Log.v("Tag","MovieDetailObserver : movieDetailModel == null");
        }
    }
}
