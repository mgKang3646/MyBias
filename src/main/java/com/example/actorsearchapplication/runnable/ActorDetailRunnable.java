package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.ActorDetailModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.ActorDetailResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;

import retrofit2.Response;

public class ActorDetailRunnable implements Runnable{

    private ClientAPI clientAPI;
    private int id;

    public ActorDetailRunnable(ClientAPI clientAPI, int id){
        this.clientAPI = clientAPI;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getActorDetail(id, Credentials.API_KEY).execute();

            if(response.code() == 200){
                Log.v("Tag", "ActorDetailRunnable : code == 200");
                ActorDetailModel actorDetailModel = ((ActorDetailResponse)response.body()).getActorDetail();
                clientAPI.getActorDetail().postValue(actorDetailModel);
            }else{
                Log.v("Tag", "ActorDetailRunnable : code != 200");
            }

        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag", "ActorDetailRunnable error");
        }

    }
}
