package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.FilmographyModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.FilmographyResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class FilmographyRunnable implements Runnable{

    private ClientAPI clientAPI;
    private int id;

    public FilmographyRunnable(ClientAPI clientAPI, int id){
        this.clientAPI = clientAPI;
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getFlimography(id, Credentials.API_KEY).execute();

            if(response.code() == 200){
                Log.v("Tag","FilmographyRunnable code == 200");
                List<FilmographyModel> filmography = new ArrayList<>(((FilmographyResponse)response.body()).getFilmography());
                clientAPI.getFilmography().postValue(filmography);

            }else{
                Log.v("Tag","FilmographyRunnable code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag","FilmographyRunnable error");
        }

    }
}
