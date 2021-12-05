package com.example.actorsearchapplication.adapters;



import android.graphics.Color;
import android.graphics.ColorFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.ActorDetailActivity;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.observer.SNSIdObserver;
import com.example.actorsearchapplication.utils.MVVMFactory;
import com.example.actorsearchapplication.viewutil.GlideUtil;
import com.example.actorsearchapplication.viewutil.LottieUtil;
import com.example.actorsearchapplication.viewutil.RecyclerViewUtil;

public class ActorDetailViewHolder {

    private ActorModel actorModel;
    private ImageView iv_actor;
    private TextView tv_popularity_actor, tv_name_actor, tv_birth_actor, tv_add_overview_actor, tv_overview_actor, tv_birth_place_actor;
    private View view;
    private LottieAnimationView heart_anime;
    private RecyclerView recyclerViewSNS;
    private RecyclerViewUtil recyclerViewUtil;

    private String overView;
    private int id;
    private boolean isClickedAddOverView = false;
    private boolean isClickedDibs = false;
    private ActorDetailActivity actorDetailActivity;

    public ActorDetailViewHolder(View view, ActorDetailActivity actorDetailActivity){
        this.actorDetailActivity = actorDetailActivity;
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
        recyclerViewSNS = view.findViewById(R.id.recyclerView_sns);
    }

    private void setClickEvent(){
        setDibsClickEvent();
        setAddInformationClickEvent();
    }

    public void onBind(ActorDetailModel actorDetailModel){
        id = actorDetailModel.getId();
        actorModel = new ActorModel(actorDetailModel);
        overView = actorDetailModel.getBiography(); //OverView 한 단락으로 줄이기
        tv_birth_actor.setText(actorDetailModel.getBirthday());
        tv_birth_place_actor.setText(actorDetailModel.getPlace_of_birth());
        tv_name_actor.setText(actorDetailModel.getName());
        tv_popularity_actor.setText(Math.round(actorDetailModel.getPopularity()*10)/10.0+"");
        GlideUtil.loadProfileImage(view.getContext(),actorDetailModel.getProfile_path(),iv_actor);
        setLottieView();
        setRecyclerViewUtil();
    }

    private void setRecyclerViewUtil(){
        recyclerViewUtil = new RecyclerViewUtil(recyclerViewSNS,new SNSRecyclerViewAdapter(actorDetailActivity));
        recyclerViewUtil.setLayoutManagerHorizontal(view.getContext());
        actorDetailActivity.getActorDetailViewModel().getSNSId().observe(actorDetailActivity,new SNSIdObserver(recyclerViewUtil.getRecyclerViewAdapter()));
        actorDetailActivity.getActorDetailViewModel().requestSNSId(id);
    }

    private void setDibsClickEvent(){
        heart_anime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClickedDibs){ // 찜 하기
                    LottieUtil.setColor(heart_anime,Color.parseColor("#e92b50"));
                    MVVMFactory.getRoomUtil(view.getContext()).insertActor(actorModel);
                    Toast.makeText(view.getContext(),"찜!",Toast.LENGTH_SHORT).show();
                    isClickedDibs = true;
                }else{ // 찜 제거
                    LottieUtil.setColor(heart_anime,Color.parseColor("#F6F6F6"));
                    MVVMFactory.getRoomUtil(view.getContext()).deleteActor(actorModel);
                    Toast.makeText(view.getContext(),"안 찜!",Toast.LENGTH_SHORT).show();
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

    private void setLottieView(){
        if(MVVMFactory.getRoomUtil(view.getContext()).isDibsActor(actorModel.getId())){
            LottieUtil.setColor(heart_anime,Color.parseColor("#e92b50"));
            isClickedDibs = true;
        }else{
            LottieUtil.setColor(heart_anime,Color.parseColor("#F6F6F6"));
            isClickedDibs = false;
        }
    }
}
