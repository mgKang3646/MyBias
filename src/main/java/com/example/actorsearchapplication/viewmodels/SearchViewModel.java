package com.example.actorsearchapplication.viewmodels;

import android.widget.ListAdapter;

import androidx.lifecycle.MutableLiveData;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

import java.util.List;

public class SearchViewModel {
    public MutableLiveData<List<ActorModel>> getSearchedActors(){
        return MVVMFactory.getMainRepository().getSearchedActors();
    }
    public void requestSearchActor(String name){
        MVVMFactory.getMainRepository().requestSearchActor(name);
    }
}
