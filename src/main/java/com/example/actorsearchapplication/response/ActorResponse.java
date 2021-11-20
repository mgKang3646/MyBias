package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.ActorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ActorResponse {

    @SerializedName("results")
    @Expose
    private ActorModel actor;

    public ActorModel getActor(){ return actor; }
}
