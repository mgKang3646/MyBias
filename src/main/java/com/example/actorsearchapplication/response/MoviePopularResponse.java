package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.MovieModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MoviePopularResponse {

    @SerializedName("results")
    @Expose
    private List<MovieModel> movies;

    public List<MovieModel> getMovies(){
        return movies;
    }
}
