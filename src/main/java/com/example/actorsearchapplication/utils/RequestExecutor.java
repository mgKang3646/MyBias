package com.example.actorsearchapplication.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class RequestExecutor {

    private final ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);

    public ScheduledExecutorService getScheduledExecutorService(){
        return scheduledExecutorService;
    }

    public void cancelRunnable(Runnable cancelRunnable){
        scheduledExecutorService.schedule(cancelRunnable,1000, TimeUnit.MILLISECONDS);
    }
}
