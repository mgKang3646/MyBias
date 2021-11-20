package com.example.actorsearchapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.lang.reflect.Array;

public class TrendModel implements Parcelable {

    private boolean adult;
    private String backdrop_path;
    private String original_language;
    private int[] genre_ids;
    private String original_title;
    private String poster_path;
    private boolean video;
    private String title;
    private int vote_count;
    private String overview;
    private String release_date;
    private float vote_average;
    private int id;
    private float popularity;
    private String media_type;

    public TrendModel(boolean adult, String backdrop_path, String original_language, int[] genre_ids, String original_title, String poster_path, boolean video, String title, int vote_count, String overview, String release_date, float vote_average, int id, float popularity, String media_type) {
        this.adult = adult;
        this.backdrop_path = backdrop_path;
        this.original_language = original_language;
        this.genre_ids = genre_ids;
        this.original_title = original_title;
        this.poster_path = poster_path;
        this.video = video;
        this.title = title;
        this.vote_count = vote_count;
        this.overview = overview;
        this.release_date = release_date;
        this.vote_average = vote_average;
        this.id = id;
        this.popularity = popularity;
        this.media_type = media_type;
    }

    protected TrendModel(Parcel in) {
        adult = in.readByte() != 0;
        backdrop_path = in.readString();
        original_language = in.readString();
        genre_ids = in.createIntArray();
        original_title = in.readString();
        poster_path = in.readString();
        video = in.readByte() != 0;
        title = in.readString();
        vote_count = in.readInt();
        overview = in.readString();
        release_date = in.readString();
        vote_average = in.readFloat();
        id = in.readInt();
        popularity = in.readFloat();
        media_type = in.readString();
    }

    public static final Creator<TrendModel> CREATOR = new Creator<TrendModel>() {
        @Override
        public TrendModel createFromParcel(Parcel in) {
            return new TrendModel(in);
        }

        @Override
        public TrendModel[] newArray(int size) {
            return new TrendModel[size];
        }
    };

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
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

    public String getPoster_path() {
        return poster_path;
    }

    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public boolean isVideo() {
        return video;
    }

    public void setVideo(boolean video) {
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getVote_count() {
        return vote_count;
    }

    public void setVote_count(int vote_count) {
        this.vote_count = vote_count;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getRelease_date() {
        return release_date;
    }

    public void setRelease_date(String release_date) {
        this.release_date = release_date;
    }

    public float getVote_average() {
        return vote_average;
    }

    public void setVote_average(float vote_average) {
        this.vote_average = vote_average;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (adult ? 1 : 0));
        parcel.writeString(backdrop_path);
        parcel.writeString(original_language);
        parcel.writeIntArray(genre_ids);
        parcel.writeString(original_title);
        parcel.writeString(poster_path);
        parcel.writeByte((byte) (video ? 1 : 0));
        parcel.writeString(title);
        parcel.writeInt(vote_count);
        parcel.writeString(overview);
        parcel.writeString(release_date);
        parcel.writeFloat(vote_average);
        parcel.writeInt(id);
        parcel.writeFloat(popularity);
        parcel.writeString(media_type);
    }
}
