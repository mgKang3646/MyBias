package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.adapters.SNSRecyclerViewAdapter;
import com.example.actorsearchapplication.models.SNSIdModel;

public class SNSIdObserver implements Observer<SNSIdModel> {

    private SNSRecyclerViewAdapter snsRecyclerViewAdapter;

    public SNSIdObserver(RecyclerView.Adapter recyclerView){
        snsRecyclerViewAdapter = (SNSRecyclerViewAdapter)recyclerView;
    }
    @Override
    public void onChanged(SNSIdModel snsIdModel) {
        if(snsIdModel != null){
            snsRecyclerViewAdapter.setSnsModels(snsIdModel);
            snsRecyclerViewAdapter.notifyDataSetChanged();
        }else{
            Log.v("Tag","SNSIdObserver : SNSIdModel is NULL");
        }
    }
}
