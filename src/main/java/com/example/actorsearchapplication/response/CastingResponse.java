package com.example.actorsearchapplication.response;

import android.util.Log;

import com.example.actorsearchapplication.models.ActorModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CastingResponse {

    @SerializedName("cast")
    @Expose
    private List<ActorModel> casting;

    public List<ActorModel> getCasting(){
        return casting;
    }
}
