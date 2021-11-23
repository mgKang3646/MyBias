package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.FilmographyModel;

public class FilmographyRecyclerHolder extends RecyclerView.ViewHolder{

    private ImageView mainImage, iconImage;
    private TextView popularity;
    private View itemView;

    public FilmographyRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        this.itemView = itemView;
        mainImage = itemView.findViewById(R.id.iv_holder);
        iconImage = itemView.findViewById(R.id.iv_ic_holder);
        popularity = itemView.findViewById(R.id.tv_popularity_holder);
    }

    public void onBind(FilmographyModel filmographyModel){
        iconImage.setImageResource(R.drawable.ic_star);
        popularity.setText(Math.round(filmographyModel.getPopularity()*10)/100.0+"");
        Glide.with(itemView.getContext())
                .load("https://image.tmdb.org/t/p/w300/"+filmographyModel.getPoster_path()).into(mainImage);
    }



}
