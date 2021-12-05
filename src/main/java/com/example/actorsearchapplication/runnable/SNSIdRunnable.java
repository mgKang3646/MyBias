package com.example.actorsearchapplication.runnable;

import android.util.Log;

import com.example.actorsearchapplication.models.SNSIdModel;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.request.Servicey;
import com.example.actorsearchapplication.response.SnsIdResponse;
import com.example.actorsearchapplication.utils.Credentials;

import java.io.IOException;

import retrofit2.Response;

public class SNSIdRunnable implements Runnable{

    private ClientAPI clientAPI;
    private int id;

    public SNSIdRunnable(ClientAPI clientAPI,int id){
        this.clientAPI = clientAPI;
        this.id = id;
    }
    @Override
    public void run() {
        try {
            Response response = Servicey.getRestApi().getSNSId(id, Credentials.API_KEY).execute();
            if( response.code()==200){
                Log.v("Tag","SNSIdRunnable : code == 200");
                SNSIdModel snsIdModel = ((SnsIdResponse)response.body()).getSNSId();
                clientAPI.getSNSId().postValue(snsIdModel);
            }else{
                Log.v("Tag","SNSIdRunnable : code != 200");
            }
        } catch (IOException e) {
            e.printStackTrace();
            Log.v("Tag","SNSIdRunnable : error");
            clientAPI.requestSNSId(id);
        }

    }
}
