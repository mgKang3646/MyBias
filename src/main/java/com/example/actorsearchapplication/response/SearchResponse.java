package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.ActorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchResponse {

    @SerializedName("results")
    @Expose
    List<ActorModel> searchedActors;

    public List<ActorModel> getSearchedActors(){
        return searchedActors;
    }

}
