package com.example.actorsearchapplication.viewutil;

import android.app.Dialog;
import android.view.View;

import com.example.actorsearchapplication.DibsDialog;
import com.example.actorsearchapplication.MainActivity;
import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.room.DibsDto;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutHandler implements TabLayout.OnTabSelectedListener {

    private MainActivity mainActivity;

    public TabLayoutHandler(MainActivity mainActivity){
        this.mainActivity = mainActivity;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        UrlModel.setPage("1");
        if(tab.getPosition() == 0){
            mainActivity.getCategoryButton().setVisibility(View.INVISIBLE);
            mainActivity.getListViewModel().requestPopularActors();
        }
        else if(tab.getPosition() == 1) {
            mainActivity.getCategoryButton().setVisibility(View.VISIBLE);
            mainActivity.getListViewModel().requestMovies();
        }else if(tab.getPosition() == 2){
            DibsDialog dibsDialog = new DibsDialog(mainActivity);
            dibsDialog.show();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
