package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.models.ActorDetailModel;

public class ActorDetailViewAdapter {

    private View actorDetailView;
    private ActorDetailViewHolder actorDetailViewHolder;
    private ActorDetailModel actorDetailModel;

    public ActorDetailViewAdapter(View actorDetailView){
        this.actorDetailView = actorDetailView;
        this.actorDetailViewHolder = new ActorDetailViewHolder(actorDetailView);
    }

    public void setActorDetailModel(ActorDetailModel actorDetailModel){
        this.actorDetailModel = actorDetailModel;
    }

    public void onBindView(){
        actorDetailViewHolder.onBind(actorDetailModel);
    }


}
