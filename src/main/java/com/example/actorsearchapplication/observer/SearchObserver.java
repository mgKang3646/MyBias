package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.adapters.SearchRecyclerViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;

import java.util.List;

public class SearchObserver implements Observer<List<ActorModel>>{

    private SearchRecyclerViewAdapter searchRecyclerViewAdapter;

    public SearchObserver(RecyclerView.Adapter recyclerViewAdapter){
        this.searchRecyclerViewAdapter = (SearchRecyclerViewAdapter)recyclerViewAdapter;
    }

    @Override
    public void onChanged(List<ActorModel> searchedActors) {
        if(searchedActors != null){
            searchRecyclerViewAdapter.setSearchedActors(searchedActors);
            searchRecyclerViewAdapter.notifyDataSetChanged();
        }else{
            Log.v("Tag","SearcObserver : searchedActors is NULL");
        }
    }
}
