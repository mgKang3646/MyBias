package com.example.actorsearchapplication.repositories;

import androidx.lifecycle.MutableLiveData;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.FilmographyModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class MainRepository {

    private MutableLiveData<List<ActorModel>> popularActors;
    private MutableLiveData<List<TrendModel>> trends;
    private MutableLiveData<ActorDetailModel> actorDetail;
    private MutableLiveData<List<FilmographyModel>> filmography;


    public MainRepository(){
        this.popularActors = new MutableLiveData<>();
        this.trends = new MutableLiveData<>();
        this.actorDetail = new MutableLiveData<>();
        this.filmography = new MutableLiveData<>();
    }

    public MutableLiveData<List<ActorModel>> getPopularActors(){
        return popularActors;
    }
    public MutableLiveData<List<TrendModel>> getTrends() { return trends; }
    public MutableLiveData<ActorDetailModel> getActorDetail() { return actorDetail; }
    public MutableLiveData<List<FilmographyModel>> getFilmography() { return filmography; }

    public void requestPopularActors(){
        MVVMFactory.getClientAPI().requestPopularActors();
    }
    public void requestActorDetail(int id) { MVVMFactory.getClientAPI().requestActorDetail(id);}
    public void requestTrends() { MVVMFactory.getClientAPI().requestTrends(); }
    public void requestFilmography(int id){ MVVMFactory.getClientAPI().requestFilmography(id); }

    public ActorModel getSelectedActor(int position){ return popularActors.getValue().get(position); }
    public TrendModel getSelectedTrend(int position){
        return trends.getValue().get(position);
    }



}
