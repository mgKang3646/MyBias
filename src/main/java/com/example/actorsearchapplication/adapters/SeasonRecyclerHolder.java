package com.example.actorsearchapplication.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.SeasonModel;
import com.example.actorsearchapplication.viewutil.GlideUtil;

public class SeasonRecyclerHolder extends RecyclerView.ViewHolder{

    private TextView tv_season_num;
    private ImageView iv_holder_season;

    public SeasonRecyclerHolder(@NonNull View itemView) {
        super(itemView);
        setFindViewById();
    }

    private void setFindViewById(){
        tv_season_num = itemView.findViewById(R.id.tv_season_num);
        iv_holder_season = itemView.findViewById(R.id.iv_holder_season);
    }

    public void onBind(SeasonModel seasonModel){
        tv_season_num.setText("시즌 " +seasonModel.getSeason_number());
        GlideUtil.loadSeasonImage(itemView.getContext(),seasonModel.getPoster_path(),seasonModel.getTmp_poster_path(),iv_holder_season);
    }
}
