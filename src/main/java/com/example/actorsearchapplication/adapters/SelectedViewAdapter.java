package com.example.actorsearchapplication.adapters;


import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.ActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.TrendModel;

public class SelectedViewAdapter{

    public static final int MODE_SELECTED_ACTOR = 0;
    public static final int MODE_SELECTED_TREND = 1;

    private View selectedView;
    private ActorModel selectedActor;
    private TrendModel selectedTrend;
    private SelectedViewHolder selectedViewHolder;
    private int mode;

    ActivityViewListener activityViewListener;

    public SelectedViewAdapter(ActivityViewListener activityViewListener, View selectedView){
        this.activityViewListener = activityViewListener;
        this.selectedViewHolder = new SelectedViewHolder(selectedView,activityViewListener);
    }

    public void onBindView(){
        if(mode == MODE_SELECTED_ACTOR) bindOnSelectedActor();
        else if( mode == MODE_SELECTED_TREND) bindOnSelectedTrend();
    }

    public void setSelectedActor(ActorModel selectedActor) {
        this.selectedActor = selectedActor;
    }
    public void setSelectedTrend(TrendModel selectedTrend){
        this.selectedTrend = selectedTrend;
    }
    public void setSelectedView(View selectedView) {
        this.selectedView = selectedView;
    }
    public void setMode(int mode){
        this.mode = mode;
    }

    private void bindOnSelectedActor(){
        selectedViewHolder.setActorId(selectedActor.getId());
        selectedViewHolder.getSelectedName().setText(selectedActor.getName());
        selectedViewHolder.getSelectedIconImageView().setImageResource(R.drawable.ic_heart);
        selectedViewHolder.getSelectedPopularity().setText((Math.round(selectedActor.getPopularity()*10))/100.0+"");
        Glide.with(selectedView.getContext()).load("https://image.tmdb.org/t/p/w500/"+selectedActor.getProfilePath())
                .into(selectedViewHolder.getSelectedImageView());
    }

    private void bindOnSelectedTrend(){
        // 트렌드 id set하기
        selectedViewHolder.getSelectedName().setText(selectedTrend.getTitle());
        selectedViewHolder.getSelectedPopularity().setText(selectedTrend.getVote_average()+"");
        selectedViewHolder.getSelectedIconImageView().setImageResource(R.drawable.ic_star);
        Glide.with(selectedView.getContext()).load("https://image.tmdb.org/t/p/w500/"+selectedTrend.getPoster_path())
                .into(selectedViewHolder.getSelectedImageView());
    }
}
