package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.RecyclerHolderClickModel;
import com.example.actorsearchapplication.models.TvModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewHolderClickHandler;

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder implements RecyclerViewHolder{

    private ImageView mainImage, iconImage;
    private TextView popularity;
    private int mode;
    private int position;


    public MainRecyclerViewHolder(@NonNull View itemView, MainContentViewHolder mainContentViewHolder) {
        super(itemView);
        setFindViewById();
        setClickEvent(mainContentViewHolder);
    }

    private void setFindViewById(){
        mainImage = itemView.findViewById(R.id.iv_holder);
        iconImage = itemView.findViewById(R.id.iv_ic_holder);
        popularity = itemView.findViewById(R.id.tv_popularity_holder);
    }

    private void setClickEvent(MainContentViewHolder mainContentViewHolder){
        RecyclerViewHolderClickHandler recyclerViewHolderClickHandler = new RecyclerViewHolderClickHandler(this);
        recyclerViewHolderClickHandler.setMainRecyclerClickEvent(mainContentViewHolder);
    }

    public void setMode(int mode) { this.mode = mode; }

    public void onBind(ActorModel popularActor){
        popularity.setText(Math.round(popularActor.getPopularity()*10)/10.0+"");
        iconImage.setImageResource(R.drawable.ic_heart);
        GlideUtil.loadPosterImage(itemView.getContext(),popularActor.getProfile_path(),mainImage);
    }

    public void onBind(MovieModel movie){
        popularity.setText(movie.getVote_average()+"");
        iconImage.setImageResource(R.drawable.ic_star);
        GlideUtil.loadPosterImage(itemView.getContext(),movie.getPoster_path(),mainImage);
    }

    public void onBind(TvModel tv){
        popularity.setText(tv.getVote_average()+"");
        iconImage.setImageResource(R.drawable.ic_star);
        GlideUtil.loadPosterImage(itemView.getContext(),tv.getPoster_path(),mainImage);
    }

    @Override
    public View getView() {
        return itemView;
    }

    @Override
    public RecyclerHolderClickModel getRecyclerHolderClickModel() {
        RecyclerHolderClickModel recyclerHolderClickModel = new RecyclerHolderClickModel();
        recyclerHolderClickModel.setId(getAdapterPosition());
        recyclerHolderClickModel.setMode(mode);
        return recyclerHolderClickModel;
    }
}

