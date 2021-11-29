package com.example.actorsearchapplication.viewutil;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.adapters.MainViewAdapter;

public class MainViewUtil {

    private View mainView;
    private MainViewAdapter mainViewAdapter;
    private MainViewAdapterFactory mainViewAdapterFactory;

    public MainViewUtil(){
        this.mainViewAdapterFactory = new MainViewAdapterFactory();
    }

    public MainViewAdapter getMainViewAdapter(){
        return mainViewAdapter;
    }

    public void inflate(Context context ,int layout, LinearLayout parent){ mainView = View.inflate(context,layout,parent); }

    public void createSelectedViewAdapter(MainActivityViewListener mainActivityViewListener) { mainViewAdapter = mainViewAdapterFactory.getSelectedViewAdapter(mainActivityViewListener,mainView); }
    public void createActorDetailViewAdapter(){ mainViewAdapter = mainViewAdapterFactory.getActorDetailAdapter(mainView); }
    public void createMovieDetailViewAdapter(){ mainViewAdapter = mainViewAdapterFactory.getMovieDetailAdapter(mainView); }
    public void createTvDetailViewAdapter(){ mainViewAdapter = mainViewAdapterFactory.getTvDetailAdapter(mainView); }
}
