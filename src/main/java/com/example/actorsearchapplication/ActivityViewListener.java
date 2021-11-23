package com.example.actorsearchapplication;

public interface ActivityViewListener {

    void requestSwitchSelectedActor(int position);
    void requestSwitchSelectedMovie(int position);
    void requestSwitchSelectedTv(int position);
    void moveActorDetailPage(int id);

}
