package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.SelectedImageActivity;
import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.SearchResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ImageSearchedActorRunnable implements Runnable{

    private ActorModel searchedActor;

    public ImageSearchedActorRunnable(ActorModel searchedActor){
        this.searchedActor = searchedActor;
    }

    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getSearchedActors(Credentials.API_KEY,searchedActor.getName(),"1").execute();

            if(response.code() == 200){
                Log.v("Tag","ImageSearhecdActorRunnable : code == 200");
                List<ActorModel> actorModels = new ArrayList<>(((SearchResponse)response.body()).getSearchedActors());
                searchedActor.setObject(actorModels.get(0));
            }else{
                Log.v("Tag","ImageSearhecdActorRunnable : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag","ImageSearhecdActorRunnable : Error");
        }finally {
            SelectedImageActivity.NumForSearch -= 1;
        }

    }
}
