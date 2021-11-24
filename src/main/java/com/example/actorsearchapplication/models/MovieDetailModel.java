package com.example.actorsearchapplication.models;

import java.util.List;

public class MovieDetailModel {

    private int id;
    private List<GenreModel> genres;
    private String overview;
    private String poster_path;
    private String release_date;
    private int revenue;
    private int runtime;
    private String title;
    private boolean video;
    private float vote_average;
    private String status;
    private List<ProductionModel> production_companies;

    public MovieDetailModel(int id, List<GenreModel> genres, String overview, String poster_path, String release_date, int revenue, int runtime, String title, boolean video, float vote_average, String status, List<ProductionModel> production_companies) {
        this.id = id;
        this.genres = genres;
        this.overview = overview;
        this.poster_path = poster_path;
        this.release_date = release_date;
        this.revenue = revenue;
        this.runtime = runtime;
        this.title = title;
        this.video = video;
        this.vote_average = vote_average;
        this.status = status;
        this.production_companies = production_companies;
    }

    public int getId() {
        return id;
    }
    public List<GenreModel> getGenres() {
        return genres;
    }
    public String getOverview() {
        return overview;
    }
    public String getPoster_path() {
        return poster_path;
    }
    public String getRelease_date() {
        return release_date;
    }
    public int getRevenue() {
        return revenue;
    }
    public int getRuntime() {
        return runtime;
    }
    public String getTitle() {
        return title;
    }
    public boolean isVideo() {
        return video;
    }
    public float getVote_average() {
        return vote_average;
    }
    public String getStatus() {
        return status;
    }
    public List<ProductionModel> getProduction_companies() {
        return production_companies;
    }

    public String getGenresToString(){
        String genresToString = "";
        for(int i=0; i<genres.size(); i++){
            if(i != genres.size()-1) genresToString += genres.get(i).getName()+" · ";
            else genresToString += genres.get(i).getName();
        }

        return genresToString;
    }
    public String getInformation() {
        String information = "수입 : "+ getRevenueDollar() + "\n상영시간 : " + getRuntimeHour() + "\n\n" + overview;
        return information;
    }
    public String getRevenueDollar(){
        int numCount = 0;
        StringBuffer stringBufferRevenue = new StringBuffer();
        StringBuffer stringBufferRevenueChanged = new StringBuffer();
        stringBufferRevenue.append(revenue);
        stringBufferRevenue.reverse();

        for(int i=0; i<stringBufferRevenue.length(); i++){
            char number = stringBufferRevenue.charAt(i);
            numCount++;
            stringBufferRevenueChanged.append(number);

            if(numCount == 3 && i != stringBufferRevenue.length()-1){
                numCount = 0;
                stringBufferRevenueChanged.append(",");
            }
        }

        stringBufferRevenueChanged.append("$");
        return stringBufferRevenueChanged.reverse().toString();
    }
    public String getRuntimeHour(){
        int hour = runtime/60;
        int min = runtime%60;
        String runtimeHour = "";

        if(hour == 0){
            runtimeHour += min+"min";
        }
        else if(min == 0){
            runtimeHour += hour+"h";
        }
        else{
            runtimeHour += hour+"h " + min +"min";
        }
        return runtimeHour;
    }
}
