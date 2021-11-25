package com.example.actorsearchapplication;

import android.widget.Button;
import com.example.actorsearchapplication.viewmodels.ListViewModel;

public interface MainActivityViewListener {

    void requestSwitchSelectedActor(int position);
    void requestSwitchSelectedMovie(int position);
    void requestSwitchSelectedTv(int position);
    void moveActorDetailPage(int id);
    void moveMovieDetailPage(int id);
    void moveTVDetailPage(int id);
    ListViewModel getListViewModel();
    Button getCategoryButton();

}
