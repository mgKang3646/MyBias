package com.example.actorsearchapplication.observer;

import android.util.Log;

import androidx.lifecycle.Observer;

import com.example.actorsearchapplication.adapters.TvDetailViewAdapter;
import com.example.actorsearchapplication.models.TvDetailModel;

public class TvDetailObserver implements Observer<TvDetailModel> {

    private TvDetailViewAdapter tvDetailViewAdapter;

    public TvDetailObserver(TvDetailViewAdapter tvDetailViewAdapter){
        this.tvDetailViewAdapter = tvDetailViewAdapter;
    }

    @Override
    public void onChanged(TvDetailModel tvDetailModel) {
        if(tvDetailModel != null){
            tvDetailViewAdapter.setModel(tvDetailModel);
            tvDetailViewAdapter.onBind();
        }else{
            Log.v("Tag","TvDetailObserver : TvDetailModel is NULL");
        }
    }
}
