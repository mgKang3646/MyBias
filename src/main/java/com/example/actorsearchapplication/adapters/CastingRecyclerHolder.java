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

public class CastingRecyclerHolder extends RecyclerView.ViewHolder {

    private TextView tv_popularity_holder;
    private ImageView iv_holder;
    private View view;
    private int id;

    public CastingRecyclerHolder(@NonNull View itemView, ActivityClickListener activityClickListener) {
        super(itemView);
        this.view = itemView;
        tv_popularity_holder = itemView.findViewById(R.id.tv_popularity_holder);
        iv_holder = itemView.findViewById(R.id.iv_holder);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityClickListener.moveActorDetailPage(id);
            }
        });
    }

    public void onBind(ActorModel actorModel){
        id = actorModel.getId();
        tv_popularity_holder.setText(Math.round(actorModel.getPopularity()*10)/10.0+"");
        if(actorModel.getProfile_path() != null){
            Glide.with(view.getContext()).load("https://image.tmdb.org/t/p/w500/"+actorModel.getProfile_path()).into(iv_holder);
        }else{
            iv_holder.setImageResource(R.drawable.default_image);
        }
    }
}
