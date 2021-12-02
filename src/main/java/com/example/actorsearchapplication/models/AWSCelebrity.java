package com.example.actorsearchapplication.models;

public class AWSCelebrity {

    private String name;
    private float matchConfidence;

    public AWSCelebrity(String name, float matchConfidence) {
        this.name = name;
        this.matchConfidence = matchConfidence;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getMatchConfidence() {
        return matchConfidence;
    }

    public void setMatchConfidence(float matchConfidence) {
        this.matchConfidence = matchConfidence;
    }
}
