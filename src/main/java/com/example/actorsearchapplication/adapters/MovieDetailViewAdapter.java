package com.example.actorsearchapplication.adapters;

import android.view.View;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.MovieDetailModel;

public class MovieDetailViewAdapter implements MainViewAdapter {

    private View movieDetailView;
    private MovieDetailViewHolder movieDetailViewHolder;
    private MovieDetailModel movieDetailModel;

    public MovieDetailViewAdapter(View movieDetailView){
        this.movieDetailView = movieDetailView;
        this.movieDetailViewHolder = new MovieDetailViewHolder(movieDetailView);
    }

    @Override
    public void setModel(Object object){
        this.movieDetailModel = (MovieDetailModel) object;
    }

    @Override
    public void onBind(){
        movieDetailViewHolder.onBind(movieDetailModel);
    }

}
