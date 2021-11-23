package com.example.actorsearchapplication.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.models.TvModel;
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

    public void switchSelectedActor(int position){
        ActorModel actorModel = MVVMFactory.getMainRepository().getSelectedActor(position);
        selectedActor.postValue(actorModel);
    }

    public void switchSelectedMovie(int position){
        MovieModel movie = MVVMFactory.getMainRepository().getSelectedMovie(position);
        Log.v("Tag", "선택된 영화 이름 : " + movie.getTitle());
        selectedMovie.postValue(movie);
    }

    public void switchSelectedTv(int position){
        TvModel tv = MVVMFactory.getMainRepository().getSelectedTv(position);
        Log.v("Tag", "선택된 Tv 이름 : " + tv.getName());
        selectedTv.postValue(tv);
    }
}
