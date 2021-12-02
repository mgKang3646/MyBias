package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.RecyclerHolderClickModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewHolderClickHandler;

public class ImageSearchRecyclerViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolder {
    ImageView profileView;
    TextView popularityView,actorNameView,matchConfidenceView;

    private int id;

    public ImageSearchRecyclerViewHolder(@NonNull View itemView, ActivityClickListener activityClickListener) {
        super(itemView);
        onBindViewById();
        setClickEvent(activityClickListener);
    }

    public void onBindViewById(){
        profileView = itemView.findViewById(R.id.iv_holder_image_search);
        popularityView = itemView.findViewById(R.id.tv_popularity_holder_image_search);
        actorNameView = itemView.findViewById(R.id.tv_actor_name_image_search);
        matchConfidenceView = itemView.findViewById(R.id.tv_match_confidence);
    }

    public void setClickEvent(ActivityClickListener activityClickListener){
        RecyclerViewHolderClickHandler recyclerViewHolderClickHandler = new RecyclerViewHolderClickHandler(this);
        recyclerViewHolderClickHandler.setSearchRecyclerClickEvent(activityClickListener);
    }

    public void onBind(ActorModel actorModel){
        id = actorModel.getId();
        popularityView.setText(Math.round(actorModel.getPopularity()*10)/10.0+"");
        actorNameView.setText(actorModel.getName());
        matchConfidenceView.setText("매치율 : " +Math.round(actorModel.getMatchConfidence()*10)/10.0+"%");
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
