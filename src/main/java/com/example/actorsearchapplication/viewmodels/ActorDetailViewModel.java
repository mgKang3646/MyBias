package com.example.actorsearchapplication.viewmodels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.models.FilmographyModel;
import com.example.actorsearchapplication.models.SNSIdModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class ActorDetailViewModel extends ViewModel {


    public MutableLiveData<ActorDetailModel> getActorDetail(){
        return MVVMFactory.getMainRepository().getActorDetail();
    }

    public MutableLiveData<List<FilmographyModel>> getFilmography(){
        return MVVMFactory.getMainRepository().getFilmography();
    }

    public MutableLiveData<SNSIdModel> getSNSId(){
        return MVVMFactory.getMainRepository().getSnsId();
    }

    public void requestActorDetail(int id){
        MVVMFactory.getMainRepository().requestActorDetail(id);
    }

    public void requestFilmography(int id){
        MVVMFactory.getMainRepository().requestFilmography(id);
    }

    public void requestSNSId(int id){
        MVVMFactory.getMainRepository().requestSNSId(id);
    }


}
