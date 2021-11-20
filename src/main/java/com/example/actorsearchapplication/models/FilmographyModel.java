package com.example.actorsearchapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class FilmographyModel implements Parcelable {

    private int id;
    private String name;
    private String poster_path;
    private float popularity;

    public FilmographyModel(int id, String name, String poster_path, float popularity) {
        this.id = id;
        this.name = name;
        this.poster_path = poster_path;
        this.popularity = popularity;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public static Creator<FilmographyModel> getCREATOR() {
        return CREATOR;
    }

    protected FilmographyModel(Parcel in) {
    }

    public static final Creator<FilmographyModel> CREATOR = new Creator<FilmographyModel>() {
        @Override
        public FilmographyModel createFromParcel(Parcel in) {
            return new FilmographyModel(in);
        }

        @Override
        public FilmographyModel[] newArray(int size) {
            return new FilmographyModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
    }
}
