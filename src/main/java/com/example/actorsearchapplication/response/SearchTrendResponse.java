package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.TrendModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchTrendResponse {

    @SerializedName("results")
    @Expose
    private List<TrendModel> trendModels;

    public List<TrendModel> getTrends(){ return trendModels; }
}
