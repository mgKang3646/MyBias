package com.example.actorsearchapplication.models;

import java.util.List;

public class TvDetailModel {
    private int id;
    private String name;
    private String overview;
    private String first_air_date;
    private String poster_path;
    private List<GenreModel> genres;
    private List<ProductionModel> networks;
    private List<SeasonModel> seasons;
    private float vote_average;

    public TvDetailModel(int id, String name, String overview, String first_air_date, String poster_path, List<GenreModel> genres, List<ProductionModel> networks, List<SeasonModel> seasons, float vote_average) {
        this.id = id;
        this.name = name;
        this.overview = overview;
        this.first_air_date = first_air_date;
        this.poster_path = poster_path;
        this.genres = genres;
        this.networks = networks;
        this.seasons = seasons;
        this.vote_average = vote_average;
        setTmpImageIntoSeason();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getOverview() {
        return overview;
    }

    public String getFirst_air_date() {
        return first_air_date;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public List<GenreModel> getGenres() {
        return genres;
    }

    public List<ProductionModel> getNetworks() {
        return networks;
    }

    public List<SeasonModel> getSeasons() {
        return seasons;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setTmpImageIntoSeason(){
        for(SeasonModel season : seasons){
            season.setTmp_poster_path(getPoster_path());
        }
    }

    public String getGenresToString(){
        String genresToString = "";
        for(int i=0; i<genres.size(); i++){
            if(i != genres.size()-1) genresToString += genres.get(i).getName()+" · ";
            else genresToString += genres.get(i).getName();
        }
        return genresToString;
    }
}
