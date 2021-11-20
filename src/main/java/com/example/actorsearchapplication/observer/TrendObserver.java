package com.example.actorsearchapplication.observer;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.models.TrendModel;

import java.util.List;

public class TrendObserver implements Observer<List<TrendModel>> {

    MainRecyclerViewAdapter mainRecyclerViewAdapter;

    public TrendObserver(MainRecyclerViewAdapter mainRecyclerViewAdapter){
        this.mainRecyclerViewAdapter = mainRecyclerViewAdapter;
    }

    @Override
    public void onChanged(List<TrendModel> trendModels) {
        if(trendModels != null){
            mainRecyclerViewAdapter.setMode(MainRecyclerViewAdapter.MODE_TRENDS);
            mainRecyclerViewAdapter.setTrends(trendModels);
            mainRecyclerViewAdapter.notifyDataSetChanged();
            mainRecyclerViewAdapter.requestSwitchSelectedTrendToTopTrend();
        }
    }
}
