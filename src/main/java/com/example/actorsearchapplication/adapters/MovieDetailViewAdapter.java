package com.example.actorsearchapplication.adapters;

import android.view.View;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.MovieDetailModel;

public class MovieDetailViewAdapter {

    private View movieDetailView;
    private MovieDetailViewHolder movieDetailViewHolder;
    private MovieDetailModel movieDetailModel;

    public MovieDetailViewAdapter(View movieDetailView){
        this.movieDetailView = movieDetailView;
        this.movieDetailViewHolder = new MovieDetailViewHolder(movieDetailView);
    }

    public void setMovieDetailModel(MovieDetailModel movieDetailModel){
        this.movieDetailModel = movieDetailModel;
    }

    public void onBindView(){
        movieDetailViewHolder.onBind(movieDetailModel);
    }

}
