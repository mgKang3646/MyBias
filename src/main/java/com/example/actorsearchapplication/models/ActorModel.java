package com.example.actorsearchapplication.models;


import java.io.Serializable;

public class ActorModel implements Serializable {

    private String profile_path;
    private int id;
    private String name;
    private float popularity;
    private float matchConfidence;

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
