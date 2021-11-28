package com.example.actorsearchapplication.observer;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.models.MovieModel;

import java.util.List;

public class MovieObserver implements Observer<List<MovieModel>> {

    private MainRecyclerViewAdapter mainRecyclerViewAdapter;

    public MovieObserver(MainRecyclerViewAdapter mainRecyclerViewAdapter){
        this.mainRecyclerViewAdapter = mainRecyclerViewAdapter;
    }

    @Override
    public void onChanged(List<MovieModel> movieModels) {
        if(movieModels != null){
            mainRecyclerViewAdapter.setMode(MainRecyclerViewAdapter.MODE_MOVIE);
            mainRecyclerViewAdapter.setMovies(movieModels);
            mainRecyclerViewAdapter.notifyDataSetChanged();
            mainRecyclerViewAdapter.requestSwitchSelected();
        }
    }
}
