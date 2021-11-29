package com.example.actorsearchapplication.viewmodels;

import android.util.Log;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.models.TvModel;
import com.example.actorsearchapplication.observer.SelectedActorObserver;
import com.example.actorsearchapplication.observer.SelectedMovieObserver;
import com.example.actorsearchapplication.observer.SelectedTvObserver;
import com.example.actorsearchapplication.utils.MVVMFactory;

public class SelectedViewModel extends ViewModel {

    private MutableLiveData<ActorModel> selectedActor;
    private MutableLiveData<MovieModel> selectedMovie;
    private MutableLiveData<TvModel> selectedTv;


    public SelectedViewModel(){
        this.selectedActor = new MutableLiveData<>();
        this.selectedMovie = new MutableLiveData<>();
        this.selectedTv = new MutableLiveData<>();
    }

    public MutableLiveData<ActorModel> getSelectedActor() {
        return selectedActor;
    }
    public MutableLiveData<MovieModel> getSelectedMovie(){ return  selectedMovie; }
    public MutableLiveData<TvModel> getSelectedTv() { return selectedTv; }

    public void switchSelected(int mode, int position){
        if(mode == MainRecyclerViewAdapter.MODE_POPULAR_ACTORS) switchSelectedActor(position);
        else if(mode == MainRecyclerViewAdapter.MODE_MOVIE) switchSelectedMovie(position);
        else if(mode == MainRecyclerViewAdapter.MODE_TV) switchSelectedTv(position);
    }

    public void observe(LifecycleOwner owner, MainViewAdapter mainViewAdapter){
        getSelectedActor().observe(owner,new SelectedActorObserver(mainViewAdapter));
        getSelectedMovie().observe(owner,new SelectedMovieObserver(mainViewAdapter));
        getSelectedTv().observe(owner,new SelectedTvObserver(mainViewAdapter));
    }

    private void switchSelectedActor(int position){
        ActorModel actorModel = MVVMFactory.getMainRepository().getSelectedActor(position);
        selectedActor.postValue(actorModel);
    }

    private void switchSelectedMovie(int position){
        MovieModel movie = MVVMFactory.getMainRepository().getSelectedMovie(position);
        selectedMovie.postValue(movie);
    }

    private void switchSelectedTv(int position){
        TvModel tv = MVVMFactory.getMainRepository().getSelectedTv(position);
        selectedTv.postValue(tv);
    }

}
