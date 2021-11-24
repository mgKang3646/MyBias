package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.GenreModel;
import com.example.actorsearchapplication.models.MovieDetailModel;
import com.example.actorsearchapplication.models.ProductionModel;

import java.util.List;

public class MovieDetailResponse {

    private int id;
    private List<GenreModel> genres;
    private String overview;
    private String poster_path;
    private String release_date;
    private int revenue;
    private int runtime;
    private String title;
    private boolean video;
    private float vote_average;
    private String status;
    private List<ProductionModel> production_companies;

    public MovieDetailModel getMovieDetail() {
        return new MovieDetailModel(id, genres, overview, poster_path, release_date, revenue, runtime, title, video, vote_average, status,production_companies);
    }
}
