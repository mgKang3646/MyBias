package com.example.actorsearchapplication.request;

import androidx.lifecycle.MutableLiveData;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.FilmographyModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.runnable.ActorDetailRunnable;
import com.example.actorsearchapplication.runnable.CancelRunnable;
import com.example.actorsearchapplication.runnable.ActorPopularRunnable;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.runnable.FilmographyRunnable;
import com.example.actorsearchapplication.runnable.TrendRunnable;
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

    public void requestTrends(){
        TrendRunnable trendRunnable = new TrendRunnable(this);
        Future future = MVVMFactory.getRequestExecutor().getScheduledExecutorService().submit(trendRunnable);
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

    public MutableLiveData<List<ActorModel>> getPopularActors() {
        return MVVMFactory.getMainRepository().getPopularActors();
    }

    public MutableLiveData<List<TrendModel>> getTrends(){
        return MVVMFactory.getMainRepository().getTrends();
    }

    public MutableLiveData<ActorDetailModel> getActorDetail(){
        return MVVMFactory.getMainRepository().getActorDetail();
    }

    public MutableLiveData<List<FilmographyModel>> getFilmography(){
        return MVVMFactory.getMainRepository().getFilmography();
    }

}
