package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.ActorDetailActivity;
import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.MovieDetailActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.TvDetailActivity;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TvModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;

public class SelectedViewHolder {

    ImageView selectedImageView, selectedIconImageView;
    TextView selectedName;
    TextView selectedPopularity;
    MainContentViewHolder mainContentViewHolder;
    View view;

    private int id;
    private int mode;

    public SelectedViewHolder(View view, MainContentViewHolder mainContentViewHolder) {
        setView(view);
        setFindViewById();
        setClickEvent(mainContentViewHolder);
    }

    private void setView(View view){
        this.view = view;
    }

    private void setFindViewById(){
        selectedImageView = view.findViewById(R.id.iv_selected);
        selectedName = view.findViewById(R.id.tv_name_selected);
        selectedPopularity = view.findViewById(R.id.tv_popularity_selected);
        selectedIconImageView = view.findViewById(R.id.iv_ic_selected);
    }

    private void setClickEvent(MainContentViewHolder mainContentViewHolder){
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == MainRecyclerViewAdapter.MODE_POPULAR_ACTORS){
                    mainContentViewHolder.moveDetailPage(ActorDetailActivity.class,id);
                }
                else if(mode == MainRecyclerViewAdapter.MODE_MOVIE){
                    mainContentViewHolder.moveDetailPage(MovieDetailActivity.class,id);
                }
                else if(mode == MainRecyclerViewAdapter.MODE_TV){
                    mainContentViewHolder.moveDetailPage(TvDetailActivity.class,id);
                }
            }
        });
    }

    public void onBind(ActorModel actorModel){
        id = actorModel.getId();
        mode = MainRecyclerViewAdapter.MODE_POPULAR_ACTORS;
        selectedName.setText(actorModel.getName());
        selectedPopularity.setText((Math.round(actorModel.getPopularity()*10))/10.0+"");
        selectedIconImageView.setImageResource(R.drawable.ic_heart);
        GlideUtil.loadProfileImage(view.getContext(),actorModel.getProfile_path(),selectedImageView);
    }

    public void onBind(MovieModel movieModel){
        id = movieModel.getId();
        mode = MainRecyclerViewAdapter.MODE_MOVIE;
        selectedName.setText(movieModel.getTitle());
        selectedPopularity.setText(movieModel.getVote_average()+"");
        selectedIconImageView.setImageResource(R.drawable.ic_star);
        GlideUtil.loadProfileImage(view.getContext(),movieModel.getPoster_path(),selectedImageView);
    }

    public void onBind(TvModel tvModel){
        id = tvModel.getId();
        mode = MainRecyclerViewAdapter.MODE_TV;
        selectedName.setText(tvModel.getName());
        selectedPopularity.setText(tvModel.getVote_average()+"");
        selectedIconImageView.setImageResource(R.drawable.ic_star);
        GlideUtil.loadProfileImage(view.getContext(),tvModel.getPoster_path(),selectedImageView);
    }



}
