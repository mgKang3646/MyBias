package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.MovieModel;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.MoviePopularResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class MovieRunnable implements Runnable{

    private ClientAPI clientAPI;

    public MovieRunnable(ClientAPI clientAPI){
        this.clientAPI = clientAPI;
    }
    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getMovies(Credentials.API_KEY, UrlModel.getPage()).execute();

            if(response.code() == 200){
                Log.v("Tag", "MovieRunnable : code == 200");
                List<MovieModel> movies = new ArrayList<>(((MoviePopularResponse)response.body()).getMovies());
                clientAPI.getMovies().postValue(movies);
            }else{
                Log.v("Tag", "MovieRunnable : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag" ,"MovieRunnable : error");
            clientAPI.requestMovies();
        }
    }
}
