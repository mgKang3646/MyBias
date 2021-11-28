package com.example.actorsearchapplication.viewutil;

import android.graphics.Color;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class StatusBar {
    public static void setStatusBar(AppCompatActivity activity){
        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        activity.getWindow().setStatusBarColor(Color.parseColor("#FFFFFF"));
    }
}
