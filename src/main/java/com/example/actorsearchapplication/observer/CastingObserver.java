package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.adapters.CastingRecyclerAdapter;
import com.example.actorsearchapplication.models.ActorModel;

import java.util.List;

public class CastingObserver implements Observer<List<ActorModel>> {

    private CastingRecyclerAdapter castingRecyclerAdapter;

    public CastingObserver(RecyclerView.Adapter recyclerAdapter){
        this.castingRecyclerAdapter = (CastingRecyclerAdapter)recyclerAdapter;
    }
    @Override
    public void onChanged(List<ActorModel> casting) {
        if(casting != null){
            castingRecyclerAdapter.setCasting(casting);
            castingRecyclerAdapter.notifyDataSetChanged();
        }else{
            Log.v("Tag","CastingObserver : List<ActorModel> is Null");
        }
    }
}
