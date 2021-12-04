package com.example.actorsearchapplication.utils;

import android.content.Context;

import androidx.room.Room;

import com.example.actorsearchapplication.repositories.MainRepository;
import com.example.actorsearchapplication.request.ClientAPI;
import com.example.actorsearchapplication.room.MyBiasDB;

public class MVVMFactory {

    private static MainRepository mainRepository;
    private static ClientAPI clientAPI;
    private static RequestExecutor requestExecutor;
    private static RoomUtil roomUtil;

    public static MainRepository getMainRepository(){
        if(mainRepository == null){
            mainRepository = new MainRepository();
        }
        return mainRepository;
    }

    public static ClientAPI getClientAPI(){
        if(clientAPI == null){
            clientAPI = new ClientAPI();
        }
        return clientAPI;
    }

    public static RequestExecutor getRequestExecutor() {
        if (requestExecutor == null) {
            requestExecutor = new RequestExecutor();
        }
        return requestExecutor;
    }

    public static RoomUtil getRoomUtil(Context context){
        if(roomUtil == null){
            roomUtil = new RoomUtil(context);
        }
        return roomUtil;
    }
}
