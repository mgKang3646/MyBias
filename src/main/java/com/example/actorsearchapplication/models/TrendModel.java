package com.example.actorsearchapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;

public class TrendModel {

//   public static final int MOVIE = 0;
//   public static final int TV = 1;

    //공통
    private int id;
    private String backdrop_path;
    private String original_language;
    private String overview;
    private String poster_path;
    private float vote_average;
    private int vote_count;
    private float popularity;
    private int[] genre_ids;

    //MOVIE
    private String original_title;
    private String release_date;
    private String title;

    //TV
    private String first_air_date;
    private String original_name;
    private String[] origin_country;
    private String name;

    private int media_type;

    public TrendModel(int id, String backdrop_path, String original_language, String overview, String poster_path, float vote_average, int vote_count, float popularity, int[] genre_ids, String original_title, String release_date, String title, String first_air_date, String original_name, String[] origin_country, String name) {
        this.id = id;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.overview = overview;
        this.poster_path = poster_path;
        this.vote_average = vote_average;
        this.vote_count = vote_count;
        this.popularity = popularity;
        this.genre_ids = genre_ids;
        this.original_title = original_title;
        this.release_date = release_date;
        this.title = title;
        this.first_air_date = first_air_date;
        this.original_name = original_name;
        this.origin_country = origin_country;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBackdrop_path() {
        return backdrop_path;
    }

    public void setBackdrop_path(String backdrop_path) {
        this.backdrop_path = backdrop_path;
    }

    public String getOriginal_language() {
        return original_language;
    }

    public void setOriginal_language(String original_language) {
        this.original_language = original_language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public int[] getGenre_ids() {
        return genre_ids;
    }

    public void setGenre_ids(int[] genre_ids) {
        this.genre_ids = genre_ids;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public void setOriginal_title(String original_title) {
        this.original_title = original_title;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public void setFirst_air_date(String first_air_date) {
        this.first_air_date = first_air_date;
    }

    public String getOriginal_name() {
        return original_name;
    }

    public void setOriginal_name(String original_name) {
        this.original_name = original_name;
    }

    public String[] getOrigin_country() {
        return origin_country;
    }

    public void setOrigin_country(String[] origin_country) {
        this.origin_country = origin_country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMedia_type() {
        return media_type;
    }

    public void setMedia_type(int media_type) {
        this.media_type = media_type;
    }



}
