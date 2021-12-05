package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.SearchPopularActorResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class ActorPopularRunnable implements Runnable {


    ClientAPI clientAPI;

    //boolean cancelRequest;
    public ActorPopularRunnable(ClientAPI clientAPI){
        this.clientAPI = clientAPI;
    }

    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getPopularActors(Credentials.API_KEY,UrlModel.getPage()).execute();

            if(response.code() == 200) {
                List<ActorModel> tempList = new ArrayList<>(((SearchPopularActorResponse)response.body()).getActors());
                clientAPI.getPopularActors().postValue(tempList);
                Log.v("Tag", "RunnableActorPoplar : code == 200");
            }
            else{
                Log.v("Tag", "RunnableActorPoplar : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag", "RunnableActorPoplar error");
            clientAPI.requestPopularActors();
        }
    }
}
