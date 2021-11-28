package com.example.actorsearchapplication.viewmodels;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.example.actorsearchapplication.adapters.MainRecyclerViewAdapter;
import com.example.actorsearchapplication.adapters.MainViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.models.TvModel;
import com.example.actorsearchapplication.observer.ActorPopularObserver;
import com.example.actorsearchapplication.observer.MovieObserver;
import com.example.actorsearchapplication.observer.TvObserver;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<ActorModel>> getPopularActors(){
        return MVVMFactory.getMainRepository().getPopularActors();
    }

    public MutableLiveData<List<MovieModel>> getMovies(){
        return MVVMFactory.getMainRepository().getMovies();
    }

    public MutableLiveData<List<TvModel>> getTvs(){
        return MVVMFactory.getMainRepository().getTvs();
    }

    public void requestPopularActors(){
        MVVMFactory.getMainRepository().requestPopularActors();
    }
    public void requestMovies(){ MVVMFactory.getMainRepository().requestMovies(); }
    public void requestTvs() { MVVMFactory.getMainRepository().requestTvs(); }

    public void observe(LifecycleOwner owner, RecyclerView.Adapter recyclerViewAdapter){
        getPopularActors().observe(owner,new ActorPopularObserver((MainRecyclerViewAdapter)recyclerViewAdapter));
        getMovies().observe(owner,new MovieObserver((MainRecyclerViewAdapter)recyclerViewAdapter));
        getTvs().observe(owner,new TvObserver((MainRecyclerViewAdapter)recyclerViewAdapter));
    }

}
