package com.example.actorsearchapplication.request;

import androidx.lifecycle.MutableLiveData;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.FilmographyModel;
import com.example.actorsearchapplication.models.MovieDetailModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TvModel;
import com.example.actorsearchapplication.runnable.ActorDetailRunnable;
import com.example.actorsearchapplication.runnable.CancelRunnable;
import com.example.actorsearchapplication.runnable.ActorPopularRunnable;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.runnable.FilmographyRunnable;
import com.example.actorsearchapplication.runnable.MovieDetailRunnable;
import com.example.actorsearchapplication.runnable.MovieRunnable;
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
}
