package com.example.actorsearchapplication.observer;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.TvModel;

public class SelectedTvObserver implements Observer<TvModel> {

    private MainViewAdapter mainViewAdapter;

    public SelectedTvObserver(MainViewAdapter mainViewAdapter){
        this.mainViewAdapter = mainViewAdapter;
    }

    @Override
    public void onChanged(TvModel tvModel) {
        if(tvModel != null){
            mainViewAdapter.setModel(tvModel);
            mainViewAdapter.onBind();
        }
    }
}
