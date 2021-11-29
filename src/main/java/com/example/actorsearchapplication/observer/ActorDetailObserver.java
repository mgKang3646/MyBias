package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.ActorDetailViewAdapter;
import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.models.ActorDetailModel;

public class ActorDetailObserver implements Observer<ActorDetailModel>  {

    private MainViewAdapter mainViewAdapter;

    public ActorDetailObserver(MainViewAdapter mainViewAdapter){
        this.mainViewAdapter = mainViewAdapter;
    }

    @Override
    public void onChanged(ActorDetailModel actorDetailModel) {
        if(actorDetailModel != null){
            mainViewAdapter.setModel(actorDetailModel);
            mainViewAdapter.onBind();
        }else{
            Log.v("Tag","ActorDetailObserver : ActorDetailModel == null");
        }
    }
}
