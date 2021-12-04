package com.example.actorsearchapplication.utils;

import android.transition.Fade;
import android.transition.Scene;
import android.transition.Transition;
import android.transition.TransitionManager;

import com.example.actorsearchapplication.MainActivity;

public class TransitionUtil {

    public static void setSceneTransition(Scene scene){
        Transition myChangeBounds = new Fade();
        myChangeBounds.setDuration(500);
        TransitionManager.go(scene,myChangeBounds);
    }

}
