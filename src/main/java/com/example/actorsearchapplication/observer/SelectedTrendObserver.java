package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.TrendModel;

public class SelectedTrendObserver implements Observer<TrendModel> {

    SelectedViewAdapter selectedViewAdapter;

    public SelectedTrendObserver(SelectedViewAdapter selectedViewAdapter){
        this.selectedViewAdapter = selectedViewAdapter;
    }

    @Override
    public void onChanged(TrendModel trendModel) {
        if(trendModel != null){
            selectedViewAdapter.setMode(SelectedViewAdapter.MODE_SELECTED_TREND);
            selectedViewAdapter.setSelectedTrend(trendModel);
            selectedViewAdapter.onBindView();
        }
    }
}
