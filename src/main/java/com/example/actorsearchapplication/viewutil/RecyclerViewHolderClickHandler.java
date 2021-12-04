package com.example.actorsearchapplication.viewutil;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.ActorDetailActivity;
import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.MovieDetailActivity;
import com.example.actorsearchapplication.TvDetailActivity;
import com.example.actorsearchapplication.adapters.MainContentViewHolder;
import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.adapters.RecyclerViewHolder;

public class RecyclerViewHolderClickHandler {

    private RecyclerViewHolder holder;
    private View view;

    public RecyclerViewHolderClickHandler(RecyclerViewHolder holder){
        this.holder = holder;
        this.view = holder.getView();
    }

    public void setCastingRecyclerClickEvent(ActivityClickListener activityClickListener){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityClickListener.moveDetailPage(ActorDetailActivity.class,holder.getRecyclerHolderClickModel().getId());
            }
        });
    }

    public void setFilmographyRecyclerClickEvent(ActivityClickListener activityClickListener){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.getRecyclerHolderClickModel().getMediaType().equals("tv")){
                    activityClickListener.moveDetailPage(TvDetailActivity.class,holder.getRecyclerHolderClickModel().getId());
                }else{
                    activityClickListener.moveDetailPage(MovieDetailActivity.class,holder.getRecyclerHolderClickModel().getId());
                }
            }
        });
    }

    public void setMainRecyclerClickEvent(MainContentViewHolder mainContentViewHolder){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = holder.getRecyclerHolderClickModel().getMode();
                int position = holder.getRecyclerHolderClickModel().getId();
                mainContentViewHolder.requestSwitchSelected(mode,position);
            }
        });
    }

    public void setSearchRecyclerClickEvent(ActivityClickListener activityClickListener){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityClickListener.moveDetailPage(ActorDetailActivity.class, holder.getRecyclerHolderClickModel().getId());
            }
        });
    }
}
