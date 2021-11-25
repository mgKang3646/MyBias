package com.example.actorsearchapplication.adapters;

import android.view.View;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.TvDetailModel;

public class TvDetailViewAdapter {

    private TvDetailModel tvDetail;
    private View tvDetailView;
    private TvDetailViewHolder tvDetailViewHolder;


    public TvDetailViewAdapter(View tvDetailView){
        this.tvDetailView = tvDetailView;
        this.tvDetailViewHolder = new TvDetailViewHolder(tvDetailView);
    }

    public void setTvDetail(TvDetailModel tvDetail){
        this.tvDetail = tvDetail;
    }

    public void onBindView(){
        tvDetailViewHolder.onBind(tvDetail);
    }


}
