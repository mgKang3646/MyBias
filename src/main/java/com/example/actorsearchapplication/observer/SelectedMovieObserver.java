package com.example.actorsearchapplication.observer;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.MovieModel;

public class SelectedMovieObserver implements Observer<MovieModel> {

    private MainViewAdapter mainViewAdapter;

    public SelectedMovieObserver(MainViewAdapter mainViewAdapter){
        this.mainViewAdapter = mainViewAdapter;
    }

    @Override
    public void onChanged(MovieModel movieModel) {
        if(movieModel != null){
            mainViewAdapter.setModel(movieModel);
            mainViewAdapter.onBind();
        }
    }
}
