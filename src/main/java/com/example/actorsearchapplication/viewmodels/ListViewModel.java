package com.example.actorsearchapplication.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.models.TvModel;
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

}
