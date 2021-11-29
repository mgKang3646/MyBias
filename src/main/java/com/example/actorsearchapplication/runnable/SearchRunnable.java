package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.ActorModel;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.SearchResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class SearchRunnable implements Runnable {

    private ClientAPI clientAPI;
    private String name;

    public SearchRunnable(ClientAPI clientAPI, String name){
        this.clientAPI = clientAPI;
        this.name = name;
    }
    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getSearchedActors(Credentials.API_KEY,name, UrlModel.getPage()).execute();

            if(response.code() == 200){
                Log.v("Tag","SearchRunnable : code == 200");
                List<ActorModel> searchedActors = new ArrayList<>(((SearchResponse)response.body()).getSearchedActors());
                clientAPI.getSearchedActors().postValue(searchedActors);
            }else{
                Log.v("Tag","SearchRunnable : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag", "SearchRunnable : Error");
        }
    }
}
