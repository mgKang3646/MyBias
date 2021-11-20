package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.ActorDetailModel;

public class ActorDetailResponse {

    private String birthday;
    private String known_for_department;
    private int id;
    private String name;
    private String biography;
    private float popularity;
    private String place_of_birth;
    private String profile_path;

    public ActorDetailModel getActorDetail(){
        return new ActorDetailModel(birthday,known_for_department, id,name, biography, popularity, place_of_birth, profile_path);
    }
}
