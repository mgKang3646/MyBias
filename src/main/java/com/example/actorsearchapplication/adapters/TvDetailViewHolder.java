package com.example.actorsearchapplication.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.ProductionModel;
import com.example.actorsearchapplication.models.SeasonModel;
import com.example.actorsearchapplication.models.TvDetailModel;

import java.util.List;

public class TvDetailViewHolder {
    private ImageView iv_tv_detail;
    private TextView tv_popularity_tv,tv_name_tv,tv_first_air_date,tv_genres_tv,tv_add_information_tv,tv_information_tv;
    private RecyclerView recyclerView_production_tv, recyclerView_season_tv;
    private View view;
    private String overview;
    private boolean isClickedAddInform = false;

    public TvDetailViewHolder(View tvDetailView){
        this.view = tvDetailView;
        iv_tv_detail = tvDetailView.findViewById(R.id.iv_tv_detail);
        tv_popularity_tv = tvDetailView.findViewById(R.id.tv_popularity_tv);
        tv_name_tv = tvDetailView.findViewById(R.id.tv_name_tv);
        tv_first_air_date = tvDetailView.findViewById(R.id.tv_first_air_date);
        tv_add_information_tv = tvDetailView.findViewById(R.id.tv_add_information_tv);
        tv_genres_tv = tvDetailView.findViewById(R.id.tv_genres_tv);
        tv_information_tv = tvDetailView.findViewById(R.id.tv_information_tv);
        recyclerView_production_tv = tvDetailView.findViewById(R.id.recyclerView_production_tv);
        recyclerView_season_tv = tvDetailView.findViewById(R.id.recyclerView_season_tv);

        tv_add_information_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isClickedAddInform){
                    tv_add_information_tv.setText("- 더보기");
                    tv_information_tv.setText(overview);
                    isClickedAddInform = true;
                }else{
                    tv_add_information_tv.setText("+ 더보기");
                    tv_information_tv.setText("");
                    isClickedAddInform = false;
                }
            }
        });

    }

    public void onBind(TvDetailModel tvDetailModel){
        if(tvDetailModel.getPoster_path() != null){
            Glide.with(view.getContext()).load("https://image.tmdb.org/t/p/w500/"+tvDetailModel.getPoster_path()).into(iv_tv_detail);
        }else{
            iv_tv_detail.setImageResource(R.drawable.default_image);
        }

        overview = tvDetailModel.getOverview();
        tv_popularity_tv.setText(tvDetailModel.getVote_average()+"");
        tv_name_tv.setText(tvDetailModel.getName());
        tv_first_air_date.setText(tvDetailModel.getFirst_air_date());
        tv_genres_tv.setText(tvDetailModel.getGenresToString());
        setProductionRecyclerView(tvDetailModel.getNetworks());
        setSeasonRecyclerView(tvDetailModel.getSeasons());
    }

    private void setProductionRecyclerView(List<ProductionModel> productions){
        if(productions != null) {
            ProductionRecyclerAdapter productionRecyclerAdapter = new ProductionRecyclerAdapter();
            recyclerView_production_tv.setAdapter(productionRecyclerAdapter);
            recyclerView_production_tv.setLayoutManager(new LinearLayoutManager(view.getContext(),LinearLayoutManager.HORIZONTAL,false));
            productionRecyclerAdapter.setProductions(productions);
            productionRecyclerAdapter.notifyDataSetChanged();
        }
    }

    private void setSeasonRecyclerView(List<SeasonModel> seasons) {
        if (seasons != null) {
            SeasonRecyclerAdapter seasonRecyclerAdapter = new SeasonRecyclerAdapter();
            recyclerView_season_tv.setAdapter(seasonRecyclerAdapter);
            recyclerView_season_tv.setLayoutManager(new LinearLayoutManager(view.getContext(), LinearLayoutManager.HORIZONTAL, false));
            seasonRecyclerAdapter.setSeasons(seasons);
            seasonRecyclerAdapter.notifyDataSetChanged();
        }
    }

}
