package com.example.actorsearchapplication.viewutil;

import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.viewmodels.ListViewModel;
import com.google.android.material.tabs.TabLayout;

public class TabLayoutHandler implements TabLayout.OnTabSelectedListener {

    private ListViewModel listViewModel;

    public TabLayoutHandler(ListViewModel listViewModel){
        this.listViewModel = listViewModel;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        UrlModel.setPage("1");
        if(tab.getPosition() == 0) listViewModel.requestPopularActors();
        else if(tab.getPosition() == 1) listViewModel.requestTrends();
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}
