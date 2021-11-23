package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.TvModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvPopularResponse {

    @SerializedName("results")
    @Expose
    private List<TvModel> tvs;

    public List<TvModel> getTvs(){
        return tvs;
    }
}
