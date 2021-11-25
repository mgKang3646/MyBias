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

public class FilmographyRecyclerHolder extends RecyclerView.ViewHolder{

    private ImageView mainImage, iconImage;
    private TextView popularity;
    private View itemView;
    private String mediaType;
    private int id;

    public FilmographyRecyclerHolder(@NonNull View itemView, ActivityClickListener activityClickListener) {
        super(itemView);
        this.itemView = itemView;
        mainImage = itemView.findViewById(R.id.iv_holder);
        iconImage = itemView.findViewById(R.id.iv_ic_holder);
        popularity = itemView.findViewById(R.id.tv_popularity_holder);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mediaType.equals("tv")){
                    activityClickListener.moveTvDetailPage(id);
                }else{
                    activityClickListener.moveMovieDetailPage(id);
                }
            }
        });
    }

    public void onBind(FilmographyModel filmographyModel){
        id = filmographyModel.getId();
        mediaType = filmographyModel.getMedia_type();
        iconImage.setImageResource(R.drawable.ic_star);
        popularity.setText(filmographyModel.getVote_average()+"");
        if(filmographyModel.getPoster_path() != null){
            Glide.with(itemView.getContext())
                    .load("https://image.tmdb.org/t/p/w300/"+filmographyModel.getPoster_path()).into(mainImage);
        }else{
            mainImage.setImageResource(R.drawable.default_image);
        }

    }



}
