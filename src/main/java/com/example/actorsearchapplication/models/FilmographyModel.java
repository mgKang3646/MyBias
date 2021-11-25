package com.example.actorsearchapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FilmographyModel  {

    private int id;
    private String name;
    private String poster_path;
    private String media_type;
    private float vote_average;

    public FilmographyModel(int id, String name, String poster_path, String media_type, float vote_average) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.media_type = media_type;
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getMedia_type() {
        return media_type;
    }

    public float getVote_average() {
        return vote_average;
    }
}
