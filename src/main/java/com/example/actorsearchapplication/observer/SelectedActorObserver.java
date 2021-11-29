package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;

public class SelectedActorObserver implements Observer<ActorModel> {

    MainViewAdapter mainViewAdapter;

    public SelectedActorObserver(MainViewAdapter mainViewAdapter){
        this.mainViewAdapter = mainViewAdapter;
    }

    @Override
    public void onChanged(ActorModel actorModel) {
        if(actorModel != null){
            mainViewAdapter.setModel(actorModel);
            mainViewAdapter.onBind();
        }
    }
}
