package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.SeasonModel;

public class SeasonRecyclerHolder extends RecyclerView.ViewHolder{

    private TextView tv_season_num;
    private ImageView iv_holder_season;
    private View view;

    public SeasonRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView;
        tv_season_num = itemView.findViewById(R.id.tv_season_num);
        iv_holder_season = itemView.findViewById(R.id.iv_holder_season);
    }

    public void onBind(SeasonModel seasonModel){
        tv_season_num.setText("시즌 " +seasonModel.getSeason_number());
        if(seasonModel.getPoster_path() != null){
            Glide.with(view.getContext()).load("https://image.tmdb.org/t/p/w500/"+seasonModel.getPoster_path()).into(iv_holder_season);
        }else if(seasonModel.getTmp_poster_path() != null){
            Glide.with(view.getContext()).load("https://image.tmdb.org/t/p/w500/"+seasonModel.getTmp_poster_path()).into(iv_holder_season);
        }else{
            iv_holder_season.setImageResource(R.drawable.default_image);
        }
    }
}
