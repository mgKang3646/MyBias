package com.example.actorsearchapplication.adapters;

import android.view.View;

import com.example.actorsearchapplication.MainActivity;

public class MainContentViewAdapter {

    MainActivity mainActivity;
    View view;
    MainContentViewHolder mainContentViewHolder;

    public MainContentViewAdapter(MainActivity mainActivity, View view){
        this.mainActivity = mainActivity;
        this.view = view;
        this.mainContentViewHolder = new MainContentViewHolder(view,mainActivity);
    }

    public void notifyCategoryChanged(int mode){
        mainContentViewHolder.changeContents(mode);
    }
    public void requestChangeMode(int mode){ mainContentViewHolder.changeMode(mode); }


}
