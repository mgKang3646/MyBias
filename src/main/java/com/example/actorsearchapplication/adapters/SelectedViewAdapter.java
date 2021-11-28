package com.example.actorsearchapplication.adapters;


import android.view.View;

import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TvModel;

public class SelectedViewAdapter implements MainViewAdapter{

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

    @Override
    public void onBind(){
        if(mode == MODE_SELECTED_ACTOR) bindOnSelectedActor();
        else if( mode == MODE_SELECTED_MOVIE) bindOnSelectedMovie();
        else if ( mode == MODE_SELECTED_TV) bindOnSelectedTv();
    }

    @Override
    public void setModel(Object object){
        if( object instanceof ActorModel) {
            mode = MODE_SELECTED_ACTOR;
            this.selectedActor = (ActorModel)object;
        }
        else if( object instanceof MovieModel){
            mode = MODE_SELECTED_MOVIE;
            this.selectedMovie = (MovieModel)object;
        }
        else if( object instanceof  TvModel ){
            mode = MODE_SELECTED_TV;
            this.selectedTv = (TvModel)object;
        }
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
