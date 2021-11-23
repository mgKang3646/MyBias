package com.example.actorsearchapplication.adapters;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.ActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TvModel;

public class SelectedViewHolder {

    ImageView selectedImageView, selectedIconImageView;
    TextView selectedName;
    TextView selectedPopularity;
    ActivityViewListener activityViewListener;
    View selectedView;

    private int id;

    public SelectedViewHolder(View selectedView, ActivityViewListener activityViewListener) {
        selectedImageView = selectedView.findViewById(R.id.iv_selected);
        selectedName = selectedView.findViewById(R.id.tv_name_selected);
        selectedPopularity = selectedView.findViewById(R.id.tv_popularity_selected);
        selectedIconImageView = selectedView.findViewById(R.id.iv_ic_selected);

        this.activityViewListener = activityViewListener;
        this.selectedView = selectedView;

        //Actor냐 트렌드냐에 따라 구분되어야 함
        selectedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activityViewListener.moveActorDetailPage(id);
            }
        });
    }

    public void setActorId(int id){
        this.id = id;
    }
    public ImageView getSelectedImageView() {
        return selectedImageView;
    }
    public ImageView getSelectedIconImageView() {
        return selectedIconImageView;
    }
    public TextView getSelectedName() {
        return selectedName;
    }
    public TextView getSelectedPopularity() {
        return selectedPopularity;
    }

    public void onBind(ActorModel actorModel){
        id = actorModel.getId();
        selectedName.setText(actorModel.getName());
        selectedPopularity.setText((Math.round(actorModel.getPopularity()*10))/100.0+"");
        selectedIconImageView.setImageResource(R.drawable.ic_heart);
        Glide.with(selectedView.getContext()).load("https://image.tmdb.org/t/p/w500/"+actorModel.getProfilePath())
                .into(selectedImageView);
    }

    public void onBind(MovieModel movieModel){
        id = movieModel.getId();
        selectedName.setText(movieModel.getTitle());
        selectedPopularity.setText(movieModel.getVote_average()+"");
        selectedIconImageView.setImageResource(R.drawable.ic_star);
        Glide.with(selectedView.getContext()).load("https://image.tmdb.org/t/p/w500/"+movieModel.getPoster_path())
                .into(selectedImageView);
    }

    public void onBind(TvModel tvModel){
        id = tvModel.getId();
        selectedName.setText(tvModel.getName());
        selectedPopularity.setText(tvModel.getVote_average()+"");
        selectedIconImageView.setImageResource(R.drawable.ic_star);
        Glide.with(selectedView.getContext()).load("https://image.tmdb.org/t/p/w500/"+tvModel.getPoster_path())
                .into(selectedImageView);
    }

}
