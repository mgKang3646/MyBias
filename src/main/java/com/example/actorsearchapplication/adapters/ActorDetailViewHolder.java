package com.example.actorsearchapplication.adapters;



import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;
import com.example.actorsearchapplication.viewutil.LottieUtil;

public class ActorDetailViewHolder {

    private ImageView iv_actor;
    private TextView tv_popularity_actor, tv_name_actor, tv_birth_actor, tv_add_overview_actor, tv_overview_actor, tv_birth_place_actor;
    private View view;
    private LottieAnimationView heart_anime;

    private String overView;
    private boolean isClickedAddOverView = false;
    private boolean isClickedDibs = false;

    public ActorDetailViewHolder(View view){
        setView(view);
        setFindViewById();
        setClickEvent();
    }

    private void setView(View actorDetailView){
        this.view = actorDetailView;
    }

    private void setFindViewById(){
        iv_actor = view.findViewById(R.id.iv_actor);
        tv_popularity_actor = view.findViewById(R.id.tv_popularity_actor);
        tv_overview_actor = view.findViewById(R.id.tv_overview_actor);
        tv_name_actor = view.findViewById(R.id.tv_name_actor);
        tv_birth_actor = view.findViewById(R.id.tv_birth_actor);
        tv_add_overview_actor = view.findViewById(R.id.tv_add_overview_actor);
        tv_birth_place_actor = view.findViewById(R.id.tv_birth_place_actor);
        heart_anime = view.findViewById(R.id.heart_dibs);

    }

    private void setClickEvent(){
        setDibsClickEvent();
        setAddInformationClickEvent();
    }

    public void onBind(ActorDetailModel actorDetailModel){
        overView = actorDetailModel.getBiography(); //OverView 한 단락으로 줄이기
        tv_birth_actor.setText(actorDetailModel.getBirthday());
        tv_birth_place_actor.setText(actorDetailModel.getPlace_of_birth());
        tv_name_actor.setText(actorDetailModel.getName());
        tv_popularity_actor.setText(Math.round(actorDetailModel.getPopularity()*10)/10.0+"");
        GlideUtil.loadProfileImage(view.getContext(),actorDetailModel.getProfile_path(),iv_actor);
    }

    private void setDibsClickEvent(){
        LottieUtil.setColor(heart_anime,Color.parseColor("#F6F6F6"));
        heart_anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClickedDibs){
                    LottieUtil.setColor(heart_anime,Color.parseColor("#e92b50"));
                    isClickedDibs = true;
                }else{
                    LottieUtil.setColor(heart_anime,Color.parseColor("#F6F6F6"));
                    isClickedDibs = false;
                }
            }
        });
    }
    private void setAddInformationClickEvent(){
        tv_add_overview_actor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isClickedAddOverView){
                    tv_add_overview_actor.setText("+ 더보기");
                    tv_overview_actor.setText("");
                    isClickedAddOverView = false;
                }else{
                    tv_add_overview_actor.setText("- 더보기");
                    tv_overview_actor.setText(overView);
                    isClickedAddOverView = true;
                }
            }
        });
    }
}
