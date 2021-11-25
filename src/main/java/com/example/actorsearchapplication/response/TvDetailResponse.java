package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.GenreModel;
import com.example.actorsearchapplication.models.ProductionModel;
import com.example.actorsearchapplication.models.SeasonModel;
import com.example.actorsearchapplication.models.TvDetailModel;

import java.util.List;

public class TvDetailResponse {
    private int id;
    private String name;
    private String overview;
    private String first_air_date;
    private String poster_path;
    private List<GenreModel> genres;
    private List<ProductionModel> networks;
    private List<SeasonModel> seasons;
    private float vote_average;

    public TvDetailModel getTvDetail(){
        return new TvDetailModel(id,name,overview,first_air_date,poster_path,genres,networks,seasons,vote_average);
    }
}
