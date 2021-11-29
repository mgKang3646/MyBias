package com.example.actorsearchapplication.adapters;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.ActorDetailActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.RecyclerHolderClickModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewHolderClickHandler;

public class SearchRecyclerViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolder{
    ImageView profileView;
    TextView popularityView,actorNameView;

    private int id;

    public SearchRecyclerViewHolder(@NonNull View itemView, ActivityClickListener activityClickListener) {
        super(itemView);
        onBindViewById();
        setClickEvent(activityClickListener);
    }

    public void onBindViewById(){
        profileView = itemView.findViewById(R.id.iv_holder_search);
        popularityView = itemView.findViewById(R.id.tv_popularity_holder_search);
        actorNameView = itemView.findViewById(R.id.tv_actor_name_search);
    }

    public void setClickEvent(ActivityClickListener activityClickListener){
        RecyclerViewHolderClickHandler recyclerViewHolderClickHandler = new RecyclerViewHolderClickHandler(this);
        recyclerViewHolderClickHandler.setSearchRecyclerClickEvent(activityClickListener);
    }

    public void onBind(ActorModel actorModel){
        id = actorModel.getId();
        popularityView.setText(Math.round(actorModel.getPopularity()*10)/10.0+"");
        actorNameView.setText(actorModel.getName());
        GlideUtil.loadProfileImage(itemView.getContext(),actorModel.getProfile_path(),profileView);
    }

    @Override
    public View getView() {
        return itemView;
    }

    @Override
    public RecyclerHolderClickModel getRecyclerHolderClickModel() {
        RecyclerHolderClickModel recyclerHolderClickModel = new RecyclerHolderClickModel();
        recyclerHolderClickModel.setId(id);
        return recyclerHolderClickModel;
    }
}
