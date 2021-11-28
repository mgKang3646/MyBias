package com.example.actorsearchapplication.observer;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.models.TvModel;

import java.util.List;

public class TvObserver implements Observer<List<TvModel>> {
    private MainRecyclerViewAdapter mainRecyclerViewAdapter;

    public TvObserver(MainRecyclerViewAdapter mainRecyclerViewAdapter){
        this.mainRecyclerViewAdapter = mainRecyclerViewAdapter;
    }

    @Override
    public void onChanged(List<TvModel> tvModels) {
        if(tvModels != null){
            mainRecyclerViewAdapter.setMode(MainRecyclerViewAdapter.MODE_TV);
            mainRecyclerViewAdapter.setTvs(tvModels);
            mainRecyclerViewAdapter.notifyDataSetChanged();
            mainRecyclerViewAdapter.requestSwitchSelected();
        }
    }
}
