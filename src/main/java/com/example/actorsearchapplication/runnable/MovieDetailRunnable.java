package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.MovieDetailModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.MovieDetailResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;

import retrofit2.Response;

public class MovieDetailRunnable implements Runnable{

    private ClientAPI clientAPI;
    private int id;


    public MovieDetailRunnable(ClientAPI clientAPI, int id){
        this.clientAPI = clientAPI;
        this.id = id;
    }
    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getMovieDetail(id, Credentials.API_KEY).execute();

            if(response.code() == 200){
                Log.v("Tag","MovieDetailRunnable : code == 200");
                MovieDetailModel movieDetailModel = ((MovieDetailResponse)response.body()).getMovieDetail();
                clientAPI.getMovieDetail().postValue(movieDetailModel);
            }else{
                Log.v("Tag","MovieDetailRunnable : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag","MovieDetailRunnable : error");
        }
    }
}
