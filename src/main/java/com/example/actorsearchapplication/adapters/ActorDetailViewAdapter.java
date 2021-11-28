package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.models.ActorDetailModel;

public class ActorDetailViewAdapter implements MainViewAdapter {

    private View actorDetailView;
    private ActorDetailViewHolder actorDetailViewHolder;
    private ActorDetailModel actorDetailModel;

    public ActorDetailViewAdapter(View actorDetailView){
        this.actorDetailView = actorDetailView;
        this.actorDetailViewHolder = new ActorDetailViewHolder(actorDetailView);
    }

    @Override
    public void setModel(Object object){
        this.actorDetailModel = (ActorDetailModel)object;
    }
    @Override
    public void onBind(){ actorDetailViewHolder.onBind(actorDetailModel);}


}
