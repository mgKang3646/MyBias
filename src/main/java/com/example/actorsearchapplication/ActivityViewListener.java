package com.example.actorsearchapplication;

public interface ActivityViewListener {

    void requestSwitchSelectedActor(int position);
    void requestSwitchSelectedTrend(int position);
    void moveActorDetailPage(int id);

}
