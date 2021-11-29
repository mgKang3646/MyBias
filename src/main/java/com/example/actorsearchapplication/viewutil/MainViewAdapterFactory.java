package com.example.actorsearchapplication.viewutil;

import android.view.View;
import android.widget.LinearLayout;

import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.adapters.ActorDetailViewAdapter;
import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.adapters.MovieDetailViewAdapter;
import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.adapters.TvDetailViewAdapter;

public class MainViewAdapterFactory {
    public MainViewAdapter getSelectedViewAdapter(MainActivityViewListener mainActivityViewListener, View view){ return new SelectedViewAdapter(mainActivityViewListener,view); }
    public MainViewAdapter getActorDetailAdapter(View view){ return new ActorDetailViewAdapter(view); }
    public MainViewAdapter getMovieDetailAdapter(View view){ return new MovieDetailViewAdapter(view); }
    public MainViewAdapter getTvDetailAdapter(View view){
        return new TvDetailViewAdapter(view);
    }
}
