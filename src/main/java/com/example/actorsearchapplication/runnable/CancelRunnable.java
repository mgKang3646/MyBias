package com.example.actorsearchapplication.runnable;

import android.util.Log;

import java.util.concurrent.Future;

public class CancelRunnable implements Runnable{

    Future future;

    public CancelRunnable(Future future){
        this.future = future;
    }
    @Override
    public void run() {
        future.cancel(true);
    }
}
