package com.example.actorsearchapplication.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.RecyclerHolderClickModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewHolderClickHandler;

public class CastingRecyclerHolder extends RecyclerView.ViewHolder implements RecyclerViewHolder{

    private TextView tv_popularity_holder;
    private ImageView iv_holder;
    private RecyclerViewHolderClickHandler recyclerViewHolderClickHandler;
    private RecyclerHolderClickModel recyclerHolderClickModel;
    private int id;

    public CastingRecyclerHolder(@NonNull View itemView, ActivityClickListener activityClickListener) {
        super(itemView);
        setFindViewById();
        setClickEvent(activityClickListener);
    }

    private void setFindViewById(){
        tv_popularity_holder = itemView.findViewById(R.id.tv_popularity_holder);
        iv_holder = itemView.findViewById(R.id.iv_holder);
    }

    private void setClickEvent(ActivityClickListener activityClickListener){
        recyclerViewHolderClickHandler = new RecyclerViewHolderClickHandler(this);
        recyclerViewHolderClickHandler.setCastingRecyclerClickEvent(activityClickListener);
    }

    public void onBind(ActorModel actorModel){
        id = actorModel.getId();
        tv_popularity_holder.setText(Math.round(actorModel.getPopularity()*10)/10.0+"");
        GlideUtil.loadProfileImage(itemView.getContext(),actorModel.getProfile_path(),iv_holder);
    }

    @Override
    public View getView(){
        return itemView;
    }

    @Override
    public RecyclerHolderClickModel getRecyclerHolderClickModel(){
        RecyclerHolderClickModel recyclerHolderClickModel = new RecyclerHolderClickModel();
        recyclerHolderClickModel.setId(id);
        return recyclerHolderClickModel;
    }


}
