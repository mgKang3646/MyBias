package com.example.actorsearchapplication.repositories;



import androidx.lifecycle.MutableLiveData;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.FilmographyModel;
import com.example.actorsearchapplication.models.MovieDetailModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.SNSIdModel;
import com.example.actorsearchapplication.models.TvDetailModel;
import com.example.actorsearchapplication.models.TvModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class MainRepository {

    private MutableLiveData<List<ActorModel>> popularActors;
    private MutableLiveData<List<MovieModel>> movies;
    private MutableLiveData<List<TvModel>> tvs;
    private MutableLiveData<ActorDetailModel> actorDetail;
    private MutableLiveData<MovieDetailModel> movieDetail;
    private MutableLiveData<TvDetailModel> tvDetail;
    private MutableLiveData<List<FilmographyModel>> filmography;
    private MutableLiveData<List<ActorModel>> casting;
    private MutableLiveData<List<ActorModel>> searchedActors;
    private MutableLiveData<SNSIdModel> snsId;


    public MainRepository(){
        this.popularActors = new MutableLiveData<>();
        this.movies = new MutableLiveData<>();
        this.tvs = new MutableLiveData<>();
        this.actorDetail = new MutableLiveData<>();
        this.filmography = new MutableLiveData<>();
        this.movieDetail = new MutableLiveData<>();
        this.casting = new MutableLiveData<>();
        this.tvDetail = new MutableLiveData<>();
        this.searchedActors = new MutableLiveData<>();
        this.snsId = new MutableLiveData<>();
    }

    public MutableLiveData<List<ActorModel>> getPopularActors(){
        return popularActors;
    }
    public MutableLiveData<List<MovieModel>> getMovies(){ return movies; }
    public MutableLiveData<List<TvModel>> getTvs() { return tvs; }
    public MutableLiveData<List<FilmographyModel>> getFilmography() { return filmography; }
    public MutableLiveData<ActorDetailModel> getActorDetail() { return actorDetail; }
    public MutableLiveData<MovieDetailModel> getMovieDetail(){ return movieDetail;}
    public MutableLiveData<List<ActorModel>> getCasting(){ return casting; }
    public MutableLiveData<TvDetailModel> getTvDetail() { return tvDetail; }
    public MutableLiveData<List<ActorModel>> getSearchedActors(){ return searchedActors; }
    public MutableLiveData<SNSIdModel> getSnsId(){ return snsId;}

    public void requestPopularActors(){ MVVMFactory.getClientAPI().requestPopularActors();}
    public void requestMovies(){ MVVMFactory.getClientAPI().requestMovies(); }
    public void requestTvs() { MVVMFactory.getClientAPI().requestTvs(); }
    public void requestFilmography(int id){ MVVMFactory.getClientAPI().requestFilmography(id); }
    public void requestActorDetail(int id) { MVVMFactory.getClientAPI().requestActorDetail(id);}
    public void requestMovieDetail(int id) { MVVMFactory.getClientAPI().requestMovieDetail(id);}
    public void requestCasting(int id ) { MVVMFactory.getClientAPI().requestCasting(id); }
    public void requestTvDetail(int id) { MVVMFactory.getClientAPI().requestTvDetail(id);}
    public void requestCastingTv(int id) { MVVMFactory.getClientAPI().requestCastingTv(id);}
    public void requestSearchActor(String name) { MVVMFactory.getClientAPI().requestSearchActor(name);}
    public void requestSNSId(int id){ MVVMFactory.getClientAPI().requestSNSId(id);}

    public ActorModel getSelectedActor(int position){ return popularActors.getValue().get(position); }
    public MovieModel getSelectedMovie(int position){ return movies.getValue().get(position);}
    public TvModel getSelectedTv(int position){ return tvs.getValue().get(position);}

}
