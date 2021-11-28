package com.example.actorsearchapplication.observer;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.MovieModel;

public class SelectedMovieObserver implements Observer<MovieModel> {

    private SelectedViewAdapter selectedViewAdapter;

    public SelectedMovieObserver(SelectedViewAdapter selectedViewAdapter){
        this.selectedViewAdapter = selectedViewAdapter;
    }

    @Override
    public void onChanged(MovieModel movieModel) {
        if(movieModel != null){
            selectedViewAdapter.setModel(movieModel);
            selectedViewAdapter.onBind();
        }
    }
}
