package com.example.actorsearchapplication.request;

import androidx.lifecycle.MutableLiveData;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.FilmographyModel;
import com.example.actorsearchapplication.models.MovieDetailModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TvDetailModel;
import com.example.actorsearchapplication.models.TvModel;
import com.example.actorsearchapplication.runnable.ActorDetailRunnable;
import com.example.actorsearchapplication.runnable.CancelRunnable;
import com.example.actorsearchapplication.runnable.ActorPopularRunnable;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.runnable.CastingRunnable;
import com.example.actorsearchapplication.runnable.CastingTvRunnable;
import com.example.actorsearchapplication.runnable.FilmographyRunnable;
import com.example.actorsearchapplication.runnable.MovieDetailRunnable;
import com.example.actorsearchapplication.runnable.MovieRunnable;
import com.example.actorsearchapplication.runnable.SearchRunnable;
import com.example.actorsearchapplication.runnable.TvDetailRunnable;
import com.example.actorsearchapplication.runnable.TvRunnable;
import com.example.actorsearchapplication.utils.MVVMFactory;
import com.example.actorsearchapplication.utils.RequestExecutor;

import java.util.List;
import java.util.concurrent.Future;

public class ClientAPI {

    private RequestExecutor requestExecutor;

    public void requestPopularActors(){
        ActorPopularRunnable actorPopularRunnable = new ActorPopularRunnable(this);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(actorPopularRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestMovies(){
        MovieRunnable movieRunnable = new MovieRunnable(this);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(movieRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestTvs(){
        TvRunnable tvRunnable = new TvRunnable(this);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(tvRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestActorDetail(int id){
        ActorDetailRunnable actorDetailRunnable = new ActorDetailRunnable(this, id);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(actorDetailRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestFilmography(int id){
        FilmographyRunnable filmographyRunnable = new FilmographyRunnable(this,id);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(filmographyRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestMovieDetail(int id){
        MovieDetailRunnable movieDetailRunnable = new MovieDetailRunnable(this,id);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(movieDetailRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestCasting(int id){
        CastingRunnable castingRunnable = new CastingRunnable(this,id);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(castingRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestTvDetail(int id){
        TvDetailRunnable tvDetailRunnable = new TvDetailRunnable(this,id);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(tvDetailRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestCastingTv(int id){
        CastingTvRunnable castingTvRunnable = new CastingTvRunnable(this,id);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(castingTvRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public void requestSearchActor(String name){
        SearchRunnable searchRunnable = new SearchRunnable(this,name);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(searchRunnable);
        MVVMFactory.getRequestExecutor().cancelRunnable(new CancelRunnable(future));
    }

    public MutableLiveData<List<ActorModel>> getPopularActors() {
        return MVVMFactory.getMainRepository().getPopularActors();
    }
    public MutableLiveData<List<MovieModel>> getMovies(){
        return MVVMFactory.getMainRepository().getMovies();
    }
    public MutableLiveData<List<TvModel>> getTvs(){
        return MVVMFactory.getMainRepository().getTvs();
    }
    public MutableLiveData<ActorDetailModel> getActorDetail(){
        return MVVMFactory.getMainRepository().getActorDetail();
    }
    public MutableLiveData<MovieDetailModel> getMovieDetail(){
        return MVVMFactory.getMainRepository().getMovieDetail();
    }
    public MutableLiveData<List<FilmographyModel>> getFilmography(){
        return MVVMFactory.getMainRepository().getFilmography();
    }
    public MutableLiveData<List<ActorModel>> getCasting(){
        return MVVMFactory.getMainRepository().getCasting();
    }
    public MutableLiveData<TvDetailModel> getTvDetail(){
        return MVVMFactory.getMainRepository().getTvDetail();
    }
    public MutableLiveData<List<ActorModel>> getSearchedActors(){
        return MVVMFactory.getMainRepository().getSearchedActors();
    }
}
