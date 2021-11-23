package com.example.actorsearchapplication.observer;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.TvModel;

public class SelectedTvObserver implements Observer<TvModel> {

    private SelectedViewAdapter selectedViewAdapter;

    public SelectedTvObserver(SelectedViewAdapter selectedViewAdapter){
        this.selectedViewAdapter = selectedViewAdapter;
    }

    @Override
    public void onChanged(TvModel tvModel) {
        if(tvModel != null){
            selectedViewAdapter.setMode(SelectedViewAdapter.MODE_SELECTED_TV);
            selectedViewAdapter.setSelectedTv(tvModel);
            selectedViewAdapter.onBindView();
        }
    }
}
