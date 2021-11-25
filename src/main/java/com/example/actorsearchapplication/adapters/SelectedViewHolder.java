package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.MainActivityViewListener;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TvModel;

public class SelectedViewHolder {

    ImageView selectedImageView, selectedIconImageView;
    TextView selectedName;
    TextView selectedPopularity;
    MainActivityViewListener mainActivityViewListener;
    View selectedView;

    private int id;
    private int mode;

    public SelectedViewHolder(View selectedView, MainActivityViewListener mainActivityViewListener) {
        selectedImageView = selectedView.findViewById(R.id.iv_selected);
        selectedName = selectedView.findViewById(R.id.tv_name_selected);
        selectedPopularity = selectedView.findViewById(R.id.tv_popularity_selected);
        selectedIconImageView = selectedView.findViewById(R.id.iv_ic_selected);

        this.mainActivityViewListener = mainActivityViewListener;
        this.selectedView = selectedView;

        //Actor냐 트렌드냐에 따라 구분되어야 함
        selectedView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mode == MainRecyclerViewAdapter.MODE_POPULAR_ACTORS){
                    mainActivityViewListener.moveActorDetailPage(id);
                }
                else if(mode == MainRecyclerViewAdapter.MODE_MOVIE){
                    mainActivityViewListener.moveMovieDetailPage(id);
                }
                else if(mode == MainRecyclerViewAdapter.MODE_TV){
                    mainActivityViewListener.moveTVDetailPage(id);
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
        if(actorModel.getProfile_path() != null){ //Gilde 분리하기
            Glide.with(selectedView.getContext()).load("https://image.tmdb.org/t/p/w500/"+actorModel.getProfile_path())
                    .into(selectedImageView);
        }else{
            selectedImageView.setImageResource(R.drawable.default_image);
        }

    }

    public void onBind(MovieModel movieModel){
        id = movieModel.getId();
        mode = MainRecyclerViewAdapter.MODE_MOVIE;
        selectedName.setText(movieModel.getTitle());
        selectedPopularity.setText(movieModel.getVote_average()+"");
        selectedIconImageView.setImageResource(R.drawable.ic_star);
        if(movieModel.getPoster_path() != null){
            Glide.with(selectedView.getContext()).load("https://image.tmdb.org/t/p/w500/"+movieModel.getPoster_path())
                    .into(selectedImageView);
        }else{
            selectedImageView.setImageResource(R.drawable.default_image);
        }

    }

    public void onBind(TvModel tvModel){
        id = tvModel.getId();
        mode = MainRecyclerViewAdapter.MODE_TV;
        selectedName.setText(tvModel.getName());
        selectedPopularity.setText(tvModel.getVote_average()+"");
        selectedIconImageView.setImageResource(R.drawable.ic_star);
        if(tvModel.getPoster_path() != null){
            Glide.with(selectedView.getContext()).load("https://image.tmdb.org/t/p/w500/"+tvModel.getPoster_path())
                    .into(selectedImageView);
        }else{
            selectedImageView.setImageResource(R.drawable.default_image);
        }
    }

}
