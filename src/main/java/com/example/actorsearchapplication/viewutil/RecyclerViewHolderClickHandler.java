package com.example.actorsearchapplication.viewutil;

import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.MainActivityViewListener;
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
                activityClickListener.moveActorDetailPage(holder.getRecyclerHolderClickModel().getId());
            }
        });
    }

    public void setFilmographyRecyclerClickEvent(ActivityClickListener activityClickListener){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(holder.getRecyclerHolderClickModel().getMediaType().equals("tv")){
                    activityClickListener.moveTvDetailPage(holder.getRecyclerHolderClickModel().getId());
                }else{
                    activityClickListener.moveMovieDetailPage(holder.getRecyclerHolderClickModel().getId());
                }
            }
        });
    }

    public void setMainRecyclerClickEvent(MainActivityViewListener mainActivityViewListener){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = holder.getRecyclerHolderClickModel().getMode();
                int position = holder.getRecyclerHolderClickModel().getId();
                mainActivityViewListener.requestSwitchSelected(mode,position);
            }
        });
    }





}
