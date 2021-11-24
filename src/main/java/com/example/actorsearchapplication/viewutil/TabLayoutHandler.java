package com.example.actorsearchapplication.viewutil;

import android.view.View;

import com.example.actorsearchapplication.ActivityViewListener;
import com.example.actorsearchapplication.MainActivity;
import com.example.actorsearchapplication.models.UrlModel;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutHandler implements TabLayout.OnTabSelectedListener {

    private ActivityViewListener activityViewListener;

    public TabLayoutHandler(ActivityViewListener activityViewListener){
        this.activityViewListener = activityViewListener;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        UrlModel.setPage("1");
        if(tab.getPosition() == 0){
            activityViewListener.getCategoryButton().setVisibility(View.INVISIBLE);
            activityViewListener.getListViewModel().requestPopularActors();
        }
        else if(tab.getPosition() == 1) {
            activityViewListener.getCategoryButton().setVisibility(View.VISIBLE);
            activityViewListener.getListViewModel().requestMovies();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
