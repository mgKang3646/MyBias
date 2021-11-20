package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.ActorDetailViewAdapter;
import com.example.actorsearchapplication.models.ActorDetailModel;

public class ActorDetailObserver implements Observer<ActorDetailModel>  {

    private ActorDetailViewAdapter actorDetailViewAdapter;

    public ActorDetailObserver(ActorDetailViewAdapter actorDetailViewAdapter){
        this.actorDetailViewAdapter = actorDetailViewAdapter;
    }

    @Override
    public void onChanged(ActorDetailModel actorDetailModel) {
        if(actorDetailModel != null){
            actorDetailViewAdapter.setActorDetailModel(actorDetailModel);
            actorDetailViewAdapter.onBindView();
        }else{
            Log.v("Tag","ActorDetailObserver : ActorDetailModel == null");
        }
    }
}
