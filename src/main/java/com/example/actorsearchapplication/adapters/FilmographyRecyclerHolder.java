package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.ActivityClickListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.FilmographyModel;
import com.example.actorsearchapplication.models.RecyclerHolderClickModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewHolderClickHandler;

public class FilmographyRecyclerHolder extends RecyclerView.ViewHolder implements RecyclerViewHolder{

    private ImageView mainImage, iconImage;
    private TextView popularity;
    private String mediaType;
    private int id;

    public FilmographyRecyclerHolder(@NonNull View itemView, ActivityClickListener activityClickListener) {
        super(itemView);
        setFindViewById();
        setClickEvent(activityClickListener);
    }

    private void setFindViewById(){
        mainImage = itemView.findViewById(R.id.iv_holder);
        iconImage = itemView.findViewById(R.id.iv_ic_holder);
        popularity = itemView.findViewById(R.id.tv_popularity_holder);
    }

    private void setClickEvent(ActivityClickListener activityClickListener){
        RecyclerViewHolderClickHandler recyclerViewHolderClickHandler = new RecyclerViewHolderClickHandler(this);
        recyclerViewHolderClickHandler.setFilmographyRecyclerClickEvent(activityClickListener);
    }

    public void onBind(FilmographyModel filmographyModel){
        id = filmographyModel.getId();
        mediaType = filmographyModel.getMedia_type();
        iconImage.setImageResource(R.drawable.ic_star);
        popularity.setText(filmographyModel.getVote_average()+"");
        GlideUtil.loadPosterImage(itemView.getContext(),filmographyModel.getPoster_path(),mainImage);
    }

    @Override
    public View getView() {
        return itemView;
    }

    @Override
    public RecyclerHolderClickModel getRecyclerHolderClickModel() {
        RecyclerHolderClickModel recyclerHolderClickModel = new RecyclerHolderClickModel();
        recyclerHolderClickModel.setId(id);
        recyclerHolderClickModel.setMediaType(mediaType);
        return recyclerHolderClickModel;
    }
}
