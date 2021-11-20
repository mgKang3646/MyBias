package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SearchPopularActorResponse {

    // Response는 하나의 response의 body를 받는 그릇이다. 그러므로 다른 데이터 모델이랑 공유하면 안된다.
    // SerialzedName이 겹치면 안 된다.
    @SerializedName("results")
    @Expose
    private List<ActorModel> models;

    public List<ActorModel> getActors(){
        return models;
    }
}
