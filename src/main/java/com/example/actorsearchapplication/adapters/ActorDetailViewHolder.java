package com.example.actorsearchapplication.adapters;



import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorDetailModel;

public class ActorDetailViewHolder {

    private ImageView iv_actor;
    private TextView tv_popularity_actor, tv_name_actor, tv_birth_actor, tv_add_overview_actor, tv_overview_actor, tv_birth_place_actor;
    private View actorDetailView;

    private String overView;
    private boolean isClickedAddOverView = false;

    public ActorDetailViewHolder(View actorDetailView){
        this.actorDetailView = actorDetailView;
        iv_actor = actorDetailView.findViewById(R.id.iv_actor);
        tv_popularity_actor = actorDetailView.findViewById(R.id.tv_popularity_actor);
        tv_overview_actor = actorDetailView.findViewById(R.id.tv_overview_actor);
        tv_name_actor = actorDetailView.findViewById(R.id.tv_name_actor);
        tv_birth_actor = actorDetailView.findViewById(R.id.tv_birth_actor);
        tv_add_overview_actor = actorDetailView.findViewById(R.id.tv_add_overview_actor);
        tv_birth_place_actor = actorDetailView.findViewById(R.id.tv_birth_place_actor);

        tv_add_overview_actor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClickedAddOverView){
                    tv_add_overview_actor.setText("+더보기");
                    tv_overview_actor.setText("");
                    isClickedAddOverView = false;
                }else{
                    tv_add_overview_actor.setText("-더보기");
                    tv_overview_actor.setText(overView);
                    isClickedAddOverView = true;
                }
            }
        });
    }

    public ImageView getIv_actor() { return iv_actor; }
    public TextView getTv_popularity_actor() { return tv_popularity_actor; }
    public TextView getTv_name_actor() { return tv_name_actor; }
    public TextView getTv_birth_actor() { return tv_birth_actor; }

    public void onBind(ActorDetailModel actorDetailModel){
        overView = actorDetailModel.getBiography(); //OverView 한 단락으로 줄이기
        tv_birth_actor.setText(actorDetailModel.getBirthday());
        tv_birth_place_actor.setText(actorDetailModel.getPlace_of_birth());
        tv_name_actor.setText(actorDetailModel.getName());
        tv_popularity_actor.setText(Math.round(actorDetailModel.getPopularity()*10)/100.0+"");
        Glide.with(actorDetailView.getContext()).load("https://image.tmdb.org/t/p/w500/"+actorDetailModel.getProfile_path())
                .into(iv_actor);
    }
}
