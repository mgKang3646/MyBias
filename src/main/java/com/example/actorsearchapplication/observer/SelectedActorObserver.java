package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;

public class SelectedActorObserver implements Observer<ActorModel> {

    SelectedViewAdapter selectedViewAdapter;

    public SelectedActorObserver(SelectedViewAdapter selectedViewAdapter){
        this.selectedViewAdapter = selectedViewAdapter;
    }

    @Override
    public void onChanged(ActorModel actorModel) {
        if(actorModel != null){
            selectedViewAdapter.setMode(SelectedViewAdapter.MODE_SELECTED_ACTOR);
            selectedViewAdapter.setSelectedActor(actorModel);
            selectedViewAdapter.onBindView();
        }
    }
}
