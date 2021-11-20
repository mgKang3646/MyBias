package com.example.actorsearchapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ActorModel implements Parcelable {

    private String profile_path;
    private int id;
    private String name;
    private float popularity;

    public ActorModel(String profilePath, int id, String name, float popularity) {
        this.profile_path = profilePath;
        this.id = id;
        this.name = name;
        this.popularity = popularity;
    }

    protected ActorModel(Parcel in) {
    }

    public static final Creator<ActorModel> CREATOR = new Creator<ActorModel>() {
        @Override
        public ActorModel createFromParcel(Parcel in) {
            return new ActorModel(in);
        }

        @Override
        public ActorModel[] newArray(int size) {
            return new ActorModel[size];
        }
    };

    public String getProfilePath() {
        return profile_path;
    }

    public void setProfilePath(String profilePath) {
        this.profile_path = profilePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
