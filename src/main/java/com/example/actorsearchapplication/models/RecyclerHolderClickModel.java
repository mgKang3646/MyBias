package com.example.actorsearchapplication.models;


public class RecyclerHolderClickModel {

    private int id;
    private String mediaType;
    private int mode;

    public void setId(int id) {
        this.id = id;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public int getId() {
        return id;
    }

    public String getMediaType() {
        return mediaType;
    }
}
