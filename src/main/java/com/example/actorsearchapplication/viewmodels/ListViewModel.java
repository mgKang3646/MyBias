package com.example.actorsearchapplication.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class ListViewModel extends ViewModel {

    public MutableLiveData<List<ActorModel>> getPopularActors(){
        return MVVMFactory.getMainRepository().getPopularActors();
    }

    public MutableLiveData<List<TrendModel>> getTrends(){
        return MVVMFactory.getMainRepository().getTrends();
    }

    public void requestPopularActors(){
        MVVMFactory.getMainRepository().requestPopularActors();
    }
    public void requestTrends(){ MVVMFactory.getMainRepository().requestTrends(); }
}
