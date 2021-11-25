package com.example.actorsearchapplication.models;

public class SeasonModel {
    private int id;
    private int episode_count;
    private int season_number;
    private String name;
    private String air_date;
    private String overview;
    private String poster_path;
    private String tmp_poster_path;

    public int getId() {
        return id;
    }

    public int getEpisode_count() {
        return episode_count;
    }

    public int getSeason_number() {
        return season_number;
    }

    public String getName() {
        return name;
    }

    public String getAir_date() {
        return air_date;
    }

    public String getOverview() {
        return overview;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getTmp_poster_path() {
        return tmp_poster_path;
    }

    public void setTmp_poster_path(String tmp_poster_path) {
        this.tmp_poster_path = tmp_poster_path;
    }
}
