package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.CastingResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class CastingRunnable implements Runnable{

    private ClientAPI clientAPI;
    private int id;

    public CastingRunnable(ClientAPI clientAPI,int id){
        this.clientAPI = clientAPI;
        this.id = id;
    }

    @Override
    public void run() {

        try {
            Response response = Servicey.getRestApi().getCasting(id, Credentials.API_KEY).execute();

            if(response.code() == 200){
                Log.v("Tag","CastingRunnable : code == 200");
                List<ActorModel> casting = new ArrayList<>(((CastingResponse)response.body()).getCasting());
                clientAPI.getCasting().postValue(casting);
            }else{
                Log.v("Tag","CastingRunnable : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag","CastingRunnable : error");
        }
    }
}
