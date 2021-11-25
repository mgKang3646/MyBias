package com.example.actorsearchapplication.viewutil;

import android.view.View;

import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.models.UrlModel;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutHandler implements TabLayout.OnTabSelectedListener {

    private MainActivityViewListener mainActivityViewListener;

    public TabLayoutHandler(MainActivityViewListener mainActivityViewListener){
        this.mainActivityViewListener = mainActivityViewListener;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        UrlModel.setPage("1");
        if(tab.getPosition() == 0){
            mainActivityViewListener.getCategoryButton().setVisibility(View.INVISIBLE);
            mainActivityViewListener.getListViewModel().requestPopularActors();
        }
        else if(tab.getPosition() == 1) {
            mainActivityViewListener.getCategoryButton().setVisibility(View.VISIBLE);
            mainActivityViewListener.getListViewModel().requestMovies();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
