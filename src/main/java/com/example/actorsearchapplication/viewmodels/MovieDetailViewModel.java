package com.example.actorsearchapplication.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.models.MovieDetailModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

public class MovieDetailViewModel extends ViewModel {

    public MutableLiveData<MovieDetailModel> getMovieDetail(){
        return MVVMFactory.getMainRepository().getMovieDetail();
    }

    public void requestMovieDetail(int id){
        MVVMFactory.getMainRepository().requestMovieDetail(id);
    }
}
