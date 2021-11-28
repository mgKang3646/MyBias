package com.example.actorsearchapplication.adapters;

import android.view.View;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.TvDetailModel;

public class TvDetailViewAdapter implements MainViewAdapter {

    private TvDetailModel tvDetail;
    private View tvDetailView;
    private TvDetailViewHolder tvDetailViewHolder;

    public TvDetailViewAdapter(View tvDetailView){
        this.tvDetailView = tvDetailView;
        this.tvDetailViewHolder = new TvDetailViewHolder(tvDetailView);
    }

    @Override
    public void setModel(Object object){ this.tvDetail = (TvDetailModel)object; }
    @Override
    public void onBind(){
        tvDetailViewHolder.onBind(tvDetail);
    }
}
