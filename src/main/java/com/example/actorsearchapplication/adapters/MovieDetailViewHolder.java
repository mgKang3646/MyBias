package com.example.actorsearchapplication.adapters;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.actorsearchapplication.R;
import com.example.actorsearchapplication.models.MovieDetailModel;
import com.example.actorsearchapplication.models.ProductionModel;

import java.util.List;

public class MovieDetailViewHolder {

    private ImageView iv_movie_detail;
    private TextView tv_popularity_movie, tv_title_movie, tv_release_date, tv_genres, tv_add_information, tv_information;
    private View movieDetailView;
    private RecyclerView productionRecyclerView;

    private boolean isAddClicked = false;
    private String information;

    public MovieDetailViewHolder(View movieDetailView){
        this.movieDetailView = movieDetailView;
        this.iv_movie_detail = movieDetailView.findViewById(R.id.iv_movie_detail);
        this.tv_popularity_movie = movieDetailView.findViewById(R.id.tv_popularity_movie);
        this.tv_title_movie = movieDetailView.findViewById(R.id.tv_title_movie);
        this.tv_release_date = movieDetailView.findViewById(R.id.tv_release_date);
        this.tv_genres = movieDetailView.findViewById(R.id.tv_genres);
        this.tv_add_information = movieDetailView.findViewById(R.id.tv_add_information);
        this.tv_information = movieDetailView.findViewById(R.id.tv_information);
        this.productionRecyclerView = movieDetailView.findViewById(R.id.recyclerView_production);

        this.tv_add_information.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!isAddClicked){
                    tv_add_information.setText("- 더보기");
                    tv_information.setText(information);
                    isAddClicked = true;
                }
                else{
                    tv_add_information.setText("+ 더보기");
                    tv_information.setText("");
                    isAddClicked = false;
                }
            }
        });
    }

    public void onBind(MovieDetailModel movieDetailModel){
        information = movieDetailModel.getInformation(); //OverView 한 단락으로 줄이기
        tv_release_date.setText(movieDetailModel.getRelease_date());
        tv_genres.setText(movieDetailModel.getGenresToString());
        tv_title_movie.setText(movieDetailModel.getTitle());
        tv_popularity_movie.setText(movieDetailModel.getVote_average()+"");
        Glide.with(movieDetailView.getContext()).load("https://image.tmdb.org/t/p/w500/"+movieDetailModel.getPoster_path())
                .into(iv_movie_detail);
        setProductionRecyclerView(movieDetailModel.getProduction_companies());
    }

    private void setProductionRecyclerView(List<ProductionModel> productions){
        if(productions != null) {
            Log.v("Tag","영화사 이미지 URL : " + productions.get(0).getLogo_path());
            ProductionRecyclerAdapter productionRecyclerAdapter = new ProductionRecyclerAdapter();
            productionRecyclerView.setAdapter(productionRecyclerAdapter);
            productionRecyclerView.setLayoutManager(new LinearLayoutManager(movieDetailView.getContext(),LinearLayoutManager.HORIZONTAL,false));
            productionRecyclerAdapter.setProductions(productions);
            productionRecyclerAdapter.notifyDataSetChanged();
        }
    }




}
