package com.example.actorsearchapplication.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.actorsearchapplication.models.ActorModel;

@Entity( tableName = "dibs" )
public class DibsDto {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String profile_path;
    private String name;
    private float popularity;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public void changeActorModelToDto(ActorModel actorModel){
        id = actorModel.getId();
        name = actorModel.getName();
        profile_path = actorModel.getProfile_path();
        popularity = actorModel.getPopularity();
    }
}
