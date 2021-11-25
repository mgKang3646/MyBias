package com.example.actorsearchapplication.adapters;


import android.view.View;

import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
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

    MainActivityViewListener mainActivityViewListener;

    public SelectedViewAdapter(MainActivityViewListener mainActivityViewListener, View selectedView){
        this.mainActivityViewListener = mainActivityViewListener;
        this.selectedViewHolder = new SelectedViewHolder(selectedView, mainActivityViewListener);
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
