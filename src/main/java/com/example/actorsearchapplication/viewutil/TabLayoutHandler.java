package com.example.actorsearchapplication.viewutil;

import android.view.View;

import com.example.actorsearchapplication.MainActivity;
import com.example.actorsearchapplication.models.UrlModel;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutHandler implements TabLayout.OnTabSelectedListener {

    private MainActivity activity;

    public TabLayoutHandler(MainActivity activity){
        this.activity = activity;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        UrlModel.setPage("1");
        if(tab.getPosition() == 0){
            activity.getCategory_button().setVisibility(View.INVISIBLE);
            activity.getListViewModel().requestPopularActors();
        }
        else if(tab.getPosition() == 1) {
            activity.getCategory_button().setVisibility(View.VISIBLE);
            activity.getListViewModel().requestMovies();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
