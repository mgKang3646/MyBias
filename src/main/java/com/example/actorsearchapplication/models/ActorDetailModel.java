package com.example.actorsearchapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

public class ActorDetailModel implements Parcelable {

    private String birthday;
    private String known_for_department;
    private int id;
    private String name;
    private String biography;
    private float popularity;
    private String place_of_birth;
    private String profile_path;


    public ActorDetailModel(String birthday, String known_for_department, int id, String name, String biography, float popularity, String place_of_birth, String profile_path) {
        this.birthday = birthday;
        this.known_for_department = known_for_department;
        this.id = id;
        this.name = name;
        this.biography = biography;
        this.popularity = popularity;
        this.place_of_birth = place_of_birth;
        this.profile_path = profile_path;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getKnown_for_department() {
        return known_for_department;
    }

    public void setKnown_for_department(String known_for_department) {
        this.known_for_department = known_for_department;
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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public float getPopularity() {
        return popularity;
    }

    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    public String getPlace_of_birth() {
        return place_of_birth;
    }

    public void setPlace_of_birth(String place_of_birth) {
        this.place_of_birth = place_of_birth;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    protected ActorDetailModel(Parcel in) {
        birthday = in.readString();
        known_for_department = in.readString();
        id = in.readInt();
        name = in.readString();
        biography = in.readString();
        popularity = in.readFloat();
        place_of_birth = in.readString();
        profile_path = in.readString();
    }

    public static final Creator<ActorDetailModel> CREATOR = new Creator<ActorDetailModel>() {
        @Override
        public ActorDetailModel createFromParcel(Parcel in) {
            return new ActorDetailModel(in);
        }

        @Override
        public ActorDetailModel[] newArray(int size) {
            return new ActorDetailModel[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(birthday);
        parcel.writeString(known_for_department);
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(biography);
        parcel.writeFloat(popularity);
        parcel.writeString(place_of_birth);
        parcel.writeString(profile_path);
    }

    @Override
    public String toString() {
        return "ActorDetailModel{" +
                "birthday='" + birthday + '\'' +
                ", known_for_department='" + known_for_department + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", biography='" + biography + '\'' +
                ", popularity=" + popularity +
                ", place_of_birth='" + place_of_birth + '\'' +
                ", profile_path='" + profile_path + '\'' +
                '}';
    }
}
