package com.example.actorsearchapplication.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.MovieDetailModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class MovieDetailViewModel extends ViewModel {

    public MutableLiveData<MovieDetailModel> getMovieDetail(){
        return MVVMFactory.getMainRepository().getMovieDetail();
    }

    public MutableLiveData<List<ActorModel>> getCasting(){
        return MVVMFactory.getMainRepository().getCasting();
    }

    public void requestMovieDetail(int id){ MVVMFactory.getMainRepository().requestMovieDetail(id); }
    public void requestCasting(int id){
        MVVMFactory.getMainRepository().requestCasting(id);
    }
}
