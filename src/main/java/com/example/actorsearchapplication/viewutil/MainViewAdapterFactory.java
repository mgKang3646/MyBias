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


    public MainViewAdapter getSelectedViewAdapter(MainActivityViewListener mainActivityViewListener, View view){
        return new SelectedViewAdapter(mainActivityViewListener,view);
    }

    public MainViewAdapter getDetailAdapter(View view){
        switch ((Integer)view.getTag()){
            case R.layout.layout_actor_detail: return getActorDetailAdapter(view);
            case R.layout.layout_movie_detail: return getMovieDetailAdapter(view);
            case R.layout.layout_tv_detail: return getTvDetailAdapter(view);
            default: return null;
        }
    }

    private MainViewAdapter getActorDetailAdapter(View view){
        return new ActorDetailViewAdapter(view);
    }

    private MainViewAdapter getMovieDetailAdapter(View view){
        return new MovieDetailViewAdapter(view);
    }

    private MainViewAdapter getTvDetailAdapter(View view){
        return new TvDetailViewAdapter(view);
    }
}
