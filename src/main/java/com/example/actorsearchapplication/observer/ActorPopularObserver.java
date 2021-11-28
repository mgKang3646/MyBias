package com.example.actorsearchapplication.observer;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;

import java.util.List;

public class ActorPopularObserver implements Observer<List<ActorModel>> {

    MainRecyclerViewAdapter mainRecyclerViewAdapter;

    public ActorPopularObserver(MainRecyclerViewAdapter mainRecyclerViewAdapter){
        this.mainRecyclerViewAdapter = mainRecyclerViewAdapter;
    }

    @Override
    public void onChanged(List<ActorModel> actorModels) {
        if(actorModels != null){
            mainRecyclerViewAdapter.setMode(MainRecyclerViewAdapter.MODE_POPULAR_ACTORS);
            mainRecyclerViewAdapter.setActors(actorModels);
            mainRecyclerViewAdapter.notifyDataSetChanged();
            mainRecyclerViewAdapter.requestSwitchSelected();
        }
    }
}
