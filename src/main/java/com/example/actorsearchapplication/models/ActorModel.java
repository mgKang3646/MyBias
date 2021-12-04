package com.example.actorsearchapplication.models;


import com.amazonaws.services.rekognition.model.Celebrity;
import com.example.actorsearchapplication.room.DibsDto;

import java.io.Serializable;

public class ActorModel implements Serializable {
    private int id;
    private String profile_path;
    private String name;
    private float popularity;
    private float matchConfidence;

    public ActorModel(ActorModel tmpModel){
        name =tmpModel.getName();
        id = tmpModel.getId();
        popularity = tmpModel.getPopularity();
        profile_path = tmpModel.getProfile_path();
    }

    public ActorModel(DibsDto dibsDto){
        id = dibsDto.getId();
        name = dibsDto.getName();
        profile_path = dibsDto.getProfile_path();
        popularity = dibsDto.getPopularity();
    }

    public ActorModel(ActorDetailModel actorDetailModel){
        id = actorDetailModel.getId();
        name = actorDetailModel.getName();
        profile_path = actorDetailModel.getProfile_path();
        popularity = actorDetailModel.getPopularity();
    }

    public ActorModel(Celebrity celebrity){
        name = celebrity.getName();
        matchConfidence = celebrity.getMatchConfidence();
    }

    public String getProfile_path() {
        return profile_path;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPopularity() {
        return popularity;
    }

    public float getMatchConfidence() {
        return matchConfidence;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setMatchConfidence(float matchConfidence) {
        this.matchConfidence = matchConfidence;
    }

    public void setObject(ActorModel tmpModel){
        name =tmpModel.getName();
        id = tmpModel.getId();
        popularity = tmpModel.getPopularity();
        profile_path = tmpModel.getProfile_path();
    }
}
