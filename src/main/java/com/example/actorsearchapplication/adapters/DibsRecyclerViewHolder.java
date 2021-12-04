package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageButton;
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
import com.example.actorsearchapplication.viewutil.IntentUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewHolderClickHandler;

public class DibsRecyclerViewHolder extends RecyclerView.ViewHolder{
    ImageView profileView;
    TextView popularityView,actorNameView;
    DibsViewHolder owner;
    int id;

    public DibsRecyclerViewHolder(@NonNull View itemView,DibsViewHolder owner) {
        super(itemView);
        this.owner = owner;
        onBindViewById();
        setClickEvent();
    }

    private void onBindViewById(){
        profileView = itemView.findViewById(R.id.iv_holder_dibs);
        popularityView = itemView.findViewById(R.id.tv_popularity_holder_dibs);
        actorNameView = itemView.findViewById(R.id.tv_actor_name_dibs);
    }

    private void setClickEvent(){
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                owner.moveToDetailActivity(ActorDetailActivity.class,id);
            }
        });
    }

    public void onBind(ActorModel actorModel){
        id = actorModel.getId();
        popularityView.setText(Math.round(actorModel.getPopularity()*10)/10.0+"");
        actorNameView.setText(actorModel.getName());
        GlideUtil.loadProfileImage(itemView.getContext(),actorModel.getProfile_path(),profileView);
    }

}
