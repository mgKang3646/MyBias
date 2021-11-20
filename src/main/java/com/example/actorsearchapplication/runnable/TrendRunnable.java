package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.TrendModel;
import com.example.actorsearchapplication.models.UrlModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.SearchPopularActorResponse;
import com.example.actorsearchapplication.response.SearchTrendResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;

public class TrendRunnable implements Runnable{

    ClientAPI clientAPI;

    public TrendRunnable(ClientAPI clientAPI){
        this.clientAPI = clientAPI;
    }

    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getTrends(Credentials.API_KEY, UrlModel.getPage()).execute();

            if(response.code() == 200) {
                Log.v("Tag", "RunnableTrend : code == 200");
                List<TrendModel> tempList = new ArrayList<>(((SearchTrendResponse)response.body()).getTrends());
                clientAPI.getTrends().postValue(tempList);
            }
            else{
                Log.v("Tag", "RunnableTrend : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag", "RunnableTrend error");
        }

    }
}
