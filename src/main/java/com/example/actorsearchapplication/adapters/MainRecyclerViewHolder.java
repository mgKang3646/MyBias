package com.example.actorsearchapplication.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.ActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.TrendModel;

public class MainRecyclerViewHolder extends RecyclerView.ViewHolder{

    private ImageView mainImage, iconImage;
    private TextView popularity;
    private ActivityViewListener activityViewListener;
    private int mode;


    public MainRecyclerViewHolder(@NonNull View itemView, ActivityViewListener activityViewListener) {
        super(itemView);
        mainImage = itemView.findViewById(R.id.iv_holder);
        iconImage = itemView.findViewById(R.id.iv_ic_holder);
        popularity = itemView.findViewById(R.id.tv_popularity_holder);
        this.activityViewListener = activityViewListener;

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == MainRecyclerViewAdapter.MODE_POPULAR_ACTORS) activityViewListener.requestSwitchSelectedActor(getAdapterPosition());
                else if ( mode == MainRecyclerViewAdapter.MODE_TRENDS) activityViewListener.requestSwitchSelectedTrend(getAdapterPosition());
            }
        });
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void bind(ActorModel popularActor){
        popularity.setText(Math.round(popularActor.getPopularity()*10)/100.0+"");
        iconImage.setImageResource(R.drawable.ic_heart);
        Glide.with(itemView.getContext())
                .load("https://image.tmdb.org/t/p/w300/"+popularActor.getProfilePath()).into(mainImage);
    }

    public void bind(TrendModel trend){
        popularity.setText(trend.getVote_average()+"");
        iconImage.setImageResource(R.drawable.ic_star);
        Glide.with(itemView.getContext())
                .load("https://image.tmdb.org/t/p/w300/"+trend.getPoster_path()).into(mainImage);
    }


}

