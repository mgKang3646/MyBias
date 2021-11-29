package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.adapters.TvDetailViewAdapter;
import com.example.actorsearchapplication.models.TvDetailModel;

public class TvDetailObserver implements Observer<TvDetailModel> {

    private MainViewAdapter mainViewAdapter;

    public TvDetailObserver(MainViewAdapter mainViewAdapter){
        this.mainViewAdapter = mainViewAdapter;
    }

    @Override
    public void onChanged(TvDetailModel tvDetailModel) {
        if(tvDetailModel != null){
            mainViewAdapter.setModel(tvDetailModel);
            mainViewAdapter.onBind();
        }else{
            Log.v("Tag","TvDetailObserver : TvDetailModel is NULL");
        }
    }
}
