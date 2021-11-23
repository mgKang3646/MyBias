package com.example.actorsearchapplication.adapters;


import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.ActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.models.TvModel;

public class SelectedViewAdapter{

    public static final int MODE_SELECTED_ACTOR = 0;
    public static final int MODE_SELECTED_MOVIE = 1;
    public static final int MODE_SELECTED_TV = 2;

    private View selectedView;
    private ActorModel selectedActor;
    private MovieModel selectedMovie;
    private TvModel selectedTv;
    private SelectedViewHolder selectedViewHolder;
    private int mode;

    ActivityViewListener activityViewListener;

    public SelectedViewAdapter(ActivityViewListener activityViewListener, View selectedView){
        this.activityViewListener = activityViewListener;
        this.selectedViewHolder = new SelectedViewHolder(selectedView,activityViewListener);
    }

    public void onBindView(){
        if(mode == MODE_SELECTED_ACTOR) bindOnSelectedActor();
        else if( mode == MODE_SELECTED_MOVIE) bindOnSelectedMovie();
        else if ( mode == MODE_SELECTED_TV) bindOnSelectedTv();
    }

    public void setSelectedActor(ActorModel selectedActor) {
        this.selectedActor = selectedActor;
    }
    public void setSelectedMovie(MovieModel selectedMovie) { this.selectedMovie = selectedMovie; }
    public void setSelectedTv(TvModel selectedTv) { this.selectedTv = selectedTv; }

    public void setSelectedView(View selectedView) {
        this.selectedView = selectedView;
    }
    public void setMode(int mode){
        this.mode = mode;
    }

    private void bindOnSelectedActor(){
       selectedViewHolder.onBind(selectedActor);
    }

    private void bindOnSelectedMovie(){
        selectedViewHolder.onBind(selectedMovie);
    }

    private void bindOnSelectedTv(){
        selectedViewHolder.onBind(selectedTv);
    }
}
