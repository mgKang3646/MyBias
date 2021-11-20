package com.example.actorsearchapplication.utils;

import com.example.actorsearchapplication.repositories.MainRepository;
import com.example.actorsearchapplication.request.ClientAPI;

public class MVVMFactory {

    private static MainRepository mainRepository;
    private static ClientAPI clientAPI;
    private static RequestExecutor requestExecutor;

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
}
