package com.example.actorsearchapplication.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.TvDetailModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class TvDetailViewModel extends ViewModel {

    public MutableLiveData<TvDetailModel> getTvDetail(){
        return MVVMFactory.getMainRepository().getTvDetail();
    }

    public MutableLiveData<List<ActorModel>> getCastingTv(){
        return MVVMFactory.getMainRepository().getCasting();
    }

    public void requestTvDetail(int id){
        MVVMFactory.getMainRepository().requestTvDetail(id);
    }
    public void requestCastingTv(int id) {MVVMFactory.getMainRepository().requestCastingTv(id);}

}
