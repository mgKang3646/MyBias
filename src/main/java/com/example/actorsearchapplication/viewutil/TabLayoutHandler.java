package com.example.actorsearchapplication.viewutil;

import android.transition.ChangeBounds;
import android.transition.Fade;
import android.transition.Slide;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.view.Gravity;

import com.example.actorsearchapplication.MainActivity;
import com.example.actorsearchapplication.models.UrlModel;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutHandler implements TabLayout.OnTabSelectedListener {

    private MainActivity mainActivity;

    public TabLayoutHandler(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        UrlModel.setPage("1");
        if(tab.getPosition() == MainActivity.MODE_ACTOR){
            mainActivity.clickActorEvent();
        }
        else if(tab.getPosition() == MainActivity.MODE_MOVIE) {
            mainActivity.clickMovieEvent();

        }else if(tab.getPosition() == mainActivity.MODE_DIBS){
            mainActivity.clickDibsEvent();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
