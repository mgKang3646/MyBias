package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.TvModel;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.TvPopularResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class TvRunnable implements Runnable{

    private ClientAPI clientAPI;

    public TvRunnable(ClientAPI clientAPI){
        this.clientAPI = clientAPI;
    }

    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getTvs(Credentials.API_KEY, UrlModel.getPage()).execute();

            if(response.code() == 200){
                Log.v("Tag","TvRunnable : code == 200");
                List<TvModel> tvs = new ArrayList<>(((TvPopularResponse)response.body()).getTvs());
                clientAPI.getTvs().postValue(tvs);
            }else{
                Log.v("Tag","TvRunnable : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag","TvRunnable : error");
            clientAPI.requestTvs();
        }
    }
}
