package com.example.actorsearchapplication.response;

import com.example.actorsearchapplication.models.FilmographyModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FilmographyResponse {

    @SerializedName("cast")
    @Expose
    List<FilmographyModel> filmography;

    public List<FilmographyModel> getFilmography(){
        return filmography;
    }
}
