package com.example.actorsearchapplication.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.actorsearchapplication.adapters.SelectedViewAdapter;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.utils.MVVMFactory;

public class SelectedViewModel extends ViewModel {

    private MutableLiveData<ActorModel> selectedActor;
    private MutableLiveData<TrendModel> selectedTrend;

    public SelectedViewModel(){
        this.selectedActor = new MutableLiveData<>();
        this.selectedTrend = new MutableLiveData<>();
    }

    public MutableLiveData<ActorModel> getSelectedActor() {
        return selectedActor;
    }
    public MutableLiveData<TrendModel> getSelectedTrend(){ return  selectedTrend; }

    public void switchSelectedActor(int position){
        ActorModel actorModel = MVVMFactory.getMainRepository().getSelectedActor(position);
        Log.v("Tag", "선택된 배우 이름 : " + actorModel.getName());
        selectedActor.postValue(actorModel);
    }

    public void switchSelectedTrend(int position){
        TrendModel trendModel = MVVMFactory.getMainRepository().getSelectedTrend(position);
        Log.v("Tag", "선택된 트렌드 이름 : " + trendModel.getTitle());
        selectedTrend.postValue(trendModel);
    }
}
