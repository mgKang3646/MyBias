package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.TvDetailModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.TvDetailResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;

import retrofit2.Response;

public class TvDetailRunnable implements Runnable{

    private ClientAPI clientAPI;
    private int id;

    public TvDetailRunnable(ClientAPI clientAPI, int id){
        this.clientAPI = clientAPI;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getTvDetail(id, Credentials.API_KEY).execute();

            if(response.code() == 200){
                Log.v("Tag", "TvDetailRunnable : code == 200");
                TvDetailModel tvDetailModel = ((TvDetailResponse)response.body()).getTvDetail();
                clientAPI.getTvDetail().postValue(tvDetailModel);
            }else{
                Log.v("Tag", "TvDetailRunnable : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag", "TvDetailRunnable : Error");
        }
    }
}
